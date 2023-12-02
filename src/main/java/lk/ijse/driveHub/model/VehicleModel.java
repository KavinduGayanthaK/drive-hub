package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.tableDto.VehicleTableDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {
    private static Connection connection;
    public VehicleDto saveVehicle(VehicleDto vehicleDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO vehicle VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?)";

        // Use Statement.RETURN_GENERATED_KEYS to retrieve the generated keys
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, vehicleDto.getBrand());
        preparedStatement.setString(2, vehicleDto.getModel());
        preparedStatement.setInt(3, vehicleDto.getVehicleTypeId());
        preparedStatement.setString(4, vehicleDto.getIsCollectedBookCopy());
        preparedStatement.setString(5, String.valueOf(vehicleDto.getManufactureYear()));
        preparedStatement.setString(6, vehicleDto.getRegisterNumber());
        preparedStatement.setString(7, vehicleDto.getTransmissionType());
        preparedStatement.setDouble(8,vehicleDto.getPerDayRate());
        preparedStatement.setDouble(9,vehicleDto.getPerDayKm());
        preparedStatement.setDouble(10,vehicleDto.getPerAdditionalKmRate());
        preparedStatement.setInt(11, vehicleDto.getOwnerId());

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                vehicleDto.setId(generatedId);
                return vehicleDto;
            }
        }
        return null;
    }

    public List<VehicleDto> getAllVehicle() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehicle";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<VehicleDto> vehicleDto = new ArrayList<>();

        while (resultSet.next()) {
            LocalDate manufactureYear = LocalDate.parse(resultSet.getString(6));
            vehicleDto.add(new VehicleDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    manufactureYear,
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getDouble(9),
                    resultSet.getDouble(10),
                    resultSet.getDouble(11),
                    resultSet.getInt(12)
            ));
        }

        return vehicleDto;
    }

    public List<VehicleTableDto> getVehicleTableDto () throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT " +
                "v.id," +
                "v.brand," +
                "v.model," +
                "t.name," +
                "v.isCollectedBookCopy," +
                "v.manufactureYear," +
                "v.registeredNumber," +
                "v.transMissionType " +
                "FROM vehicle v JOIN vehicleType t ON v.vehicleTypeId = t.id";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<VehicleTableDto> vehicleTableDtoList = new ArrayList<>();
        while (resultSet.next()) {
            vehicleTableDtoList.add(
                    new VehicleTableDto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)
                    ));
        }
        return vehicleTableDtoList;
    }


    public boolean updateVehicle(VehicleDto vehicleDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE vehicle SET " +
                "brand = ?," +
                "model = ?," +
                "vehicleTypeId = ?," +
                " isCollectedBookCopy = ?," +
                " manufactureYear = ?," +
                " registeredNumber = ?," +
                " transMissionType = ?," +
                " perDayRate = ?," +
                " perDayKm = ?," +
                " perAdditionalKmRate = ? WHERE id = ? ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,vehicleDto.getBrand());
        preparedStatement.setString(2,vehicleDto.getModel());
        preparedStatement.setInt(3,vehicleDto.getVehicleTypeId());
        preparedStatement.setString(4,vehicleDto.getIsCollectedBookCopy());
        preparedStatement.setString(5, String.valueOf(vehicleDto.getManufactureYear()));
        preparedStatement.setString(6,vehicleDto.getRegisterNumber());
        preparedStatement.setString(7,vehicleDto.getTransmissionType());
        preparedStatement.setDouble(8,vehicleDto.getPerDayRate());
        preparedStatement.setDouble(9,vehicleDto.getPerDayKm());
        preparedStatement.setDouble(10,vehicleDto.getPerAdditionalKmRate());
        preparedStatement.setInt(11,vehicleDto.getId());

        boolean updateVehicle = preparedStatement.executeUpdate() > 0;

        return updateVehicle;
    }

    public boolean deleteVehicle(int vehicleId) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM vehicle WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,vehicleId);

        boolean isVehicleDeleted = preparedStatement.executeUpdate() > 0;
        return isVehicleDeleted;
    }

}
