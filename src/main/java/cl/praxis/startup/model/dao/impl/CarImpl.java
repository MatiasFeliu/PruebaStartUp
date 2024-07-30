package cl.praxis.startup.model.dao.impl;

import cl.praxis.startup.model.connection.MySqlConnection;
import cl.praxis.startup.model.dao.CarDao;
import cl.praxis.startup.model.models.CarDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarImpl implements CarDao {

    private static final String SELECT_ALL_CARS = "SELECT a.idAuto, a.nombre_auto, a.url, p.idProveedores AS id_proveedores, p.nombre_proveedores FROM autos a JOIN proveedores p ON a.id_proveedores = p.idProveedores";
    private static final String INSERT_CAR_SQL = "INSERT INTO autos (nombre_auto, url, id_proveedores) VALUES (?,?,?)";


    @Override
    public List<CarDTO> selectAllCars() {
        List<CarDTO> carDTOS = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_CARS)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idAuto");
                String carName = rs.getString("nombre_auto");
                String carUrl = rs.getString("url");
                int carProviderId = rs.getInt("id_proveedores");
                carDTOS.add(new CarDTO(id, carName, carUrl, carProviderId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carDTOS;
    }

    @Override
    public CarDTO insertCar(CarDTO carDTO) {
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_CAR_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, carDTO.getCarName());
            pstmt.setString(2, carDTO.getUrl());
            pstmt.setInt(3, carDTO.getIdProvider());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el auto, no se afectó ninguna fila.");
            }
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int carId = rs.getInt(1);
                    carDTO.setIdCar(carId);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el auto insertado en `autos`.");
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carDTO;
    }


}
