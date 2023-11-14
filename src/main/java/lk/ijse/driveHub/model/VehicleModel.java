package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.dto.VehicleTypeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {
    private static Connection connection;
    public VehicleDto saveVehicle(VehicleDto vehicleDto) throws SQLException {
        VehicleOwnerDto vehicleOwnerDto = new VehicleOwnerDto();
        VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO vehicle VALUES(NULL,?,?,?,?,?,?,?,?)";

        // Use Statement.RETURN_GENERATED_KEYS to retrieve the generated keys
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, vehicleDto.getBrand());
        preparedStatement.setString(2, vehicleDto.getModel());
        preparedStatement.setInt(3, vehicleDto.getVehicleTypeId());
        preparedStatement.setString(4, vehicleDto.getIsCollectedBookCopy());
        preparedStatement.setString(5, String.valueOf(vehicleDto.getManufactureYear()));
        preparedStatement.setString(6, vehicleDto.getRegisterNumber());
        preparedStatement.setString(7, vehicleDto.getTransmissionType());
        preparedStatement.setInt(8, vehicleDto.getOwnerId());

        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the row was inserted successfully
        if (rowsAffected > 0) {
            // Retrieve the generated keys
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            // Check if there are generated keys
            if (generatedKeys.next()) {
                // Get the generated key (assuming it's an auto-incremented ID)
                int generatedId = generatedKeys.getInt(1);

                // Set the generated ID in the vehicleDto
                vehicleDto.setId(generatedId);

                return vehicleDto;
            }
        }

        // If something went wrong or no keys were generated, return null or throw an exception
        return null;
    }



    public List<VehicleDto> getAllVehicle() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ArrayList<VehicleDto> vehicleDto = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
//        preparedStatement.e
        //VehicleDto vehicleDto1 = new VehicleDto(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDate(4));
        VehicleDto vehicleDto1 = new VehicleDto();

        while (resultSet.next()) {
//            vehicleDto1.setId(resultSet.getInt(1));
//            vehicleDto.add(
//                    new VehicleDto(
//                            resultSet.getInt(1),
//                            resultSet.getInt(2),
//                            resultSet.getString(3),
//                            resultSet.getString(4),
//
//
//                    )
//            );

        }

        return vehicleDto;
    }
}
