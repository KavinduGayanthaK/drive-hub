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
        preparedStatement.setString(5, vehicleOwnerDto.getMobileNumber()); // Is this a typo? Should it be getEmail() instead of getNic()?
        preparedStatement.setString(6, vehicleOwnerDto.getEmail());

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                vehicleOwnerDto.setId(generatedId);

                return vehicleOwnerDto;
            }
        }
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

    public boolean deleteVehicle(int ownerId) throws SQLException {
       Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM vehicleOwner WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,ownerId);

        boolean isOwnerDeleted = preparedStatement.executeUpdate() > 0;
        return isOwnerDeleted;
    }

    public boolean updateOwner(VehicleOwnerDto vehicleOwnerDto) throws SQLException {
      Connection connection = DbConnection.getInstance().getConnection();
      String sql = "UPDATE vehicleOwner SET " +
              "firstName = ?," +
              " lastName = ?," +
              " address = ?," +
              " nic = ?," +
              "number = ?, " +
              "email = ? WHERE id = ? ";

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,vehicleOwnerDto.getFirstName());
      preparedStatement.setString(2,vehicleOwnerDto.getLastName());
      preparedStatement.setString(3,vehicleOwnerDto.getAddress());
      preparedStatement.setString(4,vehicleOwnerDto.getNic());
      preparedStatement.setString(5,vehicleOwnerDto.getMobileNumber());
      preparedStatement.setString(6,vehicleOwnerDto.getEmail());
      preparedStatement.setInt(7,vehicleOwnerDto.getId());

      boolean isUpdateOwner = preparedStatement.executeUpdate() > 0;
      return isUpdateOwner;

    }

    public VehicleOwnerDto getOwnerId(String nic) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT id FROM vehicleOwner WHERE nic = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,nic);
        VehicleOwnerDto vehicleOwnerDto = new VehicleOwnerDto();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vehicleOwnerDto.setId(resultSet.getInt(1));
        }
        return vehicleOwnerDto;
    }
}
