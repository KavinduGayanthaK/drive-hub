package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleInsuranceDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleInsuranceModel {

    private static Connection connection;

    public boolean saveInsurance(VehicleInsuranceDto vehicleInsuranceDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO vehicleInsuranceDetails VALUES";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,vehicleInsuranceDto.getId());
        preparedStatement.setInt(2,vehicleInsuranceDto.getVehicleId());
        preparedStatement.setString(3,vehicleInsuranceDto.getInsuranceNumber());
        preparedStatement.setDate(4, Date.valueOf(vehicleInsuranceDto.getIssueDate()));
        preparedStatement.setDate(5, Date.valueOf(vehicleInsuranceDto.getExpiryDate()));

        boolean isSaved = preparedStatement.executeUpdate() > 0;

        return isSaved;
    }
}
