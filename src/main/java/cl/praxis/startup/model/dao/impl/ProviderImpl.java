package cl.praxis.startup.model.dao.impl;

import cl.praxis.startup.model.connection.MySqlConnection;
import cl.praxis.startup.model.dao.ProviderDao;
import cl.praxis.startup.model.models.ProviderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class ProviderImpl implements ProviderDao {
    private static final String SELECT_ALL_PROVIDERS = "SELECT p.idProveedores, p.nombre_proveedores FROM proveedores p";
    private static final String INSERT_PROVIDER_SQL = "INSERT INTO proveedores (nombre_Proveedores) VALUES (?)";
    private static final String SELECT_PROVIDER_BY_NAME = "SELECT idProveedores FROM proveedores WHERE nombre_proveedores = ?";

    @Override
    public List<ProviderDTO> selectAllProviders() {
        List<ProviderDTO> providerDTOS = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PROVIDERS)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idProveedores");
                String providerName = rs.getString("nombre_proveedores");
                providerDTOS.add(new ProviderDTO(id, providerName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return providerDTOS;
    }

    @Override
    public ProviderDTO insertProvider(ProviderDTO providerDTO) {
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_PROVIDER_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, providerDTO.getProvidersName());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el auto, no se afectó ninguna fila.");
            }
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int providerId = rs.getInt(1);
                    providerDTO.setIdProviders(providerId);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el auto insertado en `autos`.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return providerDTO;
    }

    @Override
    public ProviderDTO selectProvider(String providerName) {
        ProviderDTO providerDTO = null;

        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PROVIDER_BY_NAME)) {
            ps.setString(1, providerName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    providerDTO = new ProviderDTO();
                    providerDTO.setIdProviders(rs.getInt("idProveedores"));
                    providerDTO.setProvidersName(rs.getString("nombre_proveedores"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providerDTO;
    }

}
