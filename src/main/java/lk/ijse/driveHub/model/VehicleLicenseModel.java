package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleLicenseDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleLicenseModel {
    private static Connection connection;
    public boolean saveLicense(VehicleLicenseDto vehicleLicenseDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO vehicleLicenseDetails VALUES";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,vehicleLicenseDto.getId());
        preparedStatement.setString(3,vehicleLicenseDto.getLicenseNumber());
        preparedStatement.setDate(4, Date.valueOf(vehicleLicenseDto.getIssueDate()));
        preparedStatement.setDate(5, Date.valueOf(vehicleLicenseDto.getExpiryDate()));

        boolean isSaved = preparedStatement.executeUpdate() > 0;
        return isSaved;
    }
}
