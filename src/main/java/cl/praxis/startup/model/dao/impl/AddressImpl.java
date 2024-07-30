package cl.praxis.startup.model.dao.impl;

import cl.praxis.startup.model.connection.MySqlConnection;
import cl.praxis.startup.model.dao.AddressDao;
import cl.praxis.startup.model.models.AddressDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressImpl implements AddressDao {
    private static final String SELECT_ALL_ADDRESS = "SELECT d.idDirecciones, d.nombre_direccion, d.numero, d.id_usuario FROM direcciones d";
    private static final String INSERT_ADDRESS_SQL = "INSERT INTO direcciones (nombre_direccion, numero, id_usuario) VALUES (?,?,?)";

    @Override
    public List<AddressDTO> selectAllAddress() {
        List<AddressDTO> addressDTO = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_ADDRESS)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idDirecciones");
                String addressName = rs.getString("nombre_direccion");
                int addresNum = rs.getInt("numero");
                int idUser = rs.getInt("id_usuario");
                addressDTO.add(new AddressDTO(id, addressName, addresNum, idUser));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return addressDTO;
    }

    @Override
    public AddressDTO insertAddress(AddressDTO addressDTO) {
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_ADDRESS_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, addressDTO.getAddressName());
            pstmt.setInt(2, addressDTO.getAddressNum());
            pstmt.setInt(3, addressDTO.getIdUser());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el auto, no se afectó ninguna fila.");
            }
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int addressId = rs.getInt(1);
                    addressDTO.setIdAddress(addressId);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el auto insertado en `autos`.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return addressDTO;
    }

}
