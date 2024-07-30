package cl.praxis.startup.model.dao.impl;

import cl.praxis.startup.model.connection.MySqlConnection;
import cl.praxis.startup.model.dao.UserDao;
import cl.praxis.startup.model.models.UserDTO;


import java.sql.Timestamp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDao {

    private static final String SELECT_ALL_USERS="SELECT u.idUsuario, u.correo, u.clave, u.nombre, u.apodo, u.peso,u.id_auto, u.fecha_creacion, u.fecha_actualizacion, r.nombre_rol AS rol " +
            "FROM usuarios u " +
            "JOIN usuario_roles ur ON u.idUsuario = ur.id_usuario_rol " +
            "JOIN roles r ON ur.id_rol_rol = r.idRoles";
    private static final String SELECT_USER_BY_EMAIL ="SELECT u.idUsuario, u.correo, u.clave, u.nombre, u.apodo, u.peso, u.id_auto, u.fecha_creacion, u.fecha_actualizacion, r.nombre_rol AS rol" +
            " FROM usuarios u" +
            " JOIN usuario_roles ur ON u.idUsuario = ur.id_usuario_rol " +
            " JOIN roles r ON ur.id_rol_rol = r.idRoles" +
            " WHERE u.correo = ?";
    private static final String INSERT_USER_SQL="INSERT INTO usuarios (correo, clave, nombre, apodo, peso, id_auto, fecha_creacion, fecha_actualizacion) VALUES (?,?,?,?,?, ?, current_timestamp, current_timestamp)";
    private static final String UPDATE_USER_SQL="UPDATE usuarios SET correo = ?, clave = ?, nombre = ?, apodo = ?, peso = ?, id_auto = ?, fecha_actualizacion = CURRENT_TIMESTAMP WHERE idUsuario =?";
    private static final String DELETE_USER_SQL="DELETE FROM usuarios WHERE idUsuario = ?";
    private static final String INSERT_USER_ROLE_SQL = "INSERT INTO usuario_roles (id_usuario_rol, id_rol_rol) VALUES (?, ?)";
    private static final int ADMIN_ROLE_ID = 1;
    private static final int USER_ROLE_ID = 2;

    private static final String LOGIN="SELECT u.idUsuario, u.correo, u.clave, u.nombre, u.apodo, u.peso, u.id_auto, u.fecha_creacion, u.fecha_actualizacion, r.nombre_rol AS rol " +
            "FROM usuarios u " +
            "INNER JOIN usuario_roles ur ON u.idUsuario = ur.id_usuario_rol " +
            "INNER JOIN roles r ON ur.id_rol_rol = r.idRoles " +
            "WHERE u.correo = ? AND u.clave";

    @Override
    public UserDTO selectUser(String email) {
        UserDTO user = null;
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idUsuario");
                String email1 = rs.getString("correo");
                String password = rs.getString("clave");
                String name = rs.getString("nombre");
                String nick = rs.getString("apodo");
                int weight = rs.getInt("peso");
                int idCar = rs.getInt("id_auto");
                Timestamp createdAt = rs.getTimestamp("fecha_creacion");
                Timestamp updatedAt = rs.getTimestamp("fecha_actualizacion");
                String role = rs.getString("nombre_rol");
                user = new UserDTO(id, email1, password, name, nick, weight, idCar, createdAt, updatedAt, role);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<UserDTO> selectAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idUsuario");
                String email1 = rs.getString("correo");
                String password = rs.getString("clave");
                String name = rs.getString("nombre");
                String nick = rs.getString("apodo");
                int weight = rs.getInt("peso");
                int idCar = rs.getInt("id_auto");
                Timestamp createdAt = rs.getTimestamp("fecha_creacion");
                Timestamp updatedAt = rs.getTimestamp("fecha_actualizacion");
                String role = rs.getString("rol");
                users.add(new UserDTO(id, email1, password, name, nick, weight, idCar, createdAt, updatedAt, role));
                System.out.println("nombre rol: " + id + "" + role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public UserDTO insertUser(UserDTO user) {
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getNick());
            pstmt.setInt(5, user.getWeight());
            pstmt.setInt(6, user.getIdAuto());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el usuario, no se afectó ninguna fila.");
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el usuario insertado en `usuarios`.");
                }
            }
            insertUserRole(user, user.getRole());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    @Override
    public UserDTO updateUser(UserDTO user) {
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(UPDATE_USER_SQL)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getNick());
            pstmt.setInt(5, user.getWeight());
            pstmt.setInt(6, user.getIdAuto());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean rowDeleted;
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(DELETE_USER_SQL)){
            pstmt.setInt(1,id);
            rowDeleted = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return rowDeleted;
    }

    @Override
    public void insertUserRole(UserDTO user, String role) {
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_USER_ROLE_SQL)) {

            int roleId = role.equals("admin") ? ADMIN_ROLE_ID : USER_ROLE_ID;
            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, roleId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el rol del usuario, no se afectó ninguna fila.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public UserDTO login(String email) {

        UserDTO user = null;
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(LOGIN)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idUsuario");
                String emailDB = rs.getString("correo");
                String password = rs.getString("clave");
                String name = rs.getString("nombre");
                String nick = rs.getString("apodo");
                int weight = rs.getInt("peso");
                int idCar = rs.getInt("id_auto");
                Timestamp createdAt = rs.getTimestamp("fecha_creacion");
                Timestamp updatedAt = rs.getTimestamp("fecha_actualizacion");
                String role = rs.getString("rol");

                user = new UserDTO(id, emailDB, password, name, nick, weight, idCar, createdAt, updatedAt, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


}
