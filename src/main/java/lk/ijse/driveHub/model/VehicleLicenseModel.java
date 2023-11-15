package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleLicenseDto;

import java.sql.*;

public class VehicleLicenseModel {
    private static Connection connection;
    public VehicleLicenseDto saveLicense(VehicleLicenseDto vehicleLicenseDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO vehicleLicenseDetails VALUES (NULL,?,?,?,?)";

        // Use Statement.RETURN_GENERATED_KEYS to retrieve the generated keys
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // preparedStatement.setInt(1, vehicleLicenseDto.getId()); // Assuming ID is auto-incremented, no need to set it explicitly
        preparedStatement.setInt(1,vehicleLicenseDto.getVehicleId());
        preparedStatement.setString(2, vehicleLicenseDto.getLicenseNumber());
        preparedStatement.setString(3, String.valueOf(vehicleLicenseDto.getIssueDate()));
        preparedStatement.setString(4, String.valueOf(vehicleLicenseDto.getExpiryDate()));
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the row was inserted successfully
        if (rowsAffected > 0) {
            // Retrieve the generated keys
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            // Check if there are generated keys
            if (generatedKeys.next()) {
                // Get the generated key (assuming it's an auto-incremented ID)
                int generatedId = generatedKeys.getInt(1);

                // Set the generated ID in the vehicleLicenseDto
                vehicleLicenseDto.setId(generatedId);

                return vehicleLicenseDto;
            }
        }

        // If something went wrong or no keys were generated, return null or throw an exception
        return null;
    }
    }


