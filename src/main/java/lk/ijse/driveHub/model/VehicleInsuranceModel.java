package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleInsuranceDto;

import java.sql.*;

public class VehicleInsuranceModel {

    private static Connection connection;

    public VehicleInsuranceDto saveInsurance(VehicleInsuranceDto vehicleInsuranceDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO vehicleInsuranceDetails (vehicleId, insuranceNumber, issueDate, expiryDate) VALUES (?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, vehicleInsuranceDto.getVehicleId());
        preparedStatement.setString(2, vehicleInsuranceDto.getInsuranceNumber());
        preparedStatement.setString(3, String.valueOf(vehicleInsuranceDto.getIssueDate()));
        preparedStatement.setString(4, String.valueOf(vehicleInsuranceDto.getExpiryDate()));

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {

                int generatedId = generatedKeys.getInt(1);
                vehicleInsuranceDto.setId(generatedId);
                return vehicleInsuranceDto;
            }
        }
        return null;
    }
}
