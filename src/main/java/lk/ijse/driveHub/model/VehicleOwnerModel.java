package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleOwnerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleOwnerModel {
    public VehicleOwnerDto saveOwner(VehicleOwnerDto vehicleOwnerDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO vehicleOwner VALUES(NULL,?,?,?,?,?,?)";

        // Use Statement.RETURN_GENERATED_KEYS to retrieve the generated keys
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, vehicleOwnerDto.getFirstName());
        preparedStatement.setString(2, vehicleOwnerDto.getLastName());
        preparedStatement.setString(3, vehicleOwnerDto.getAddress());
        preparedStatement.setString(4, vehicleOwnerDto.getNic());
        preparedStatement.setString(5, vehicleOwnerDto.getNic()); // Is this a typo? Should it be getEmail() instead of getNic()?
        preparedStatement.setString(6, vehicleOwnerDto.getEmail());

        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the row was inserted successfully
        if (rowsAffected > 0) {
            // Retrieve the generated keys
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            // Check if there are generated keys
            if (generatedKeys.next()) {
                // Get the generated key (assuming it's an auto-incremented ID)
                int generatedId = generatedKeys.getInt(1);

                // Set the generated ID in the vehicleOwnerDto
                vehicleOwnerDto.setId(generatedId);

                return vehicleOwnerDto;
            }
        }

        // If something went wrong or no keys were generated, return null or throw an exception
        return null;
    }


    public static List<VehicleOwnerDto> getAllOwner() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehicleOwner";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<VehicleOwnerDto> vehicleOwnerDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            vehicleOwnerDtoArrayList.add(
                    new VehicleOwnerDto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );

        }
        return vehicleOwnerDtoArrayList;
    }
}
