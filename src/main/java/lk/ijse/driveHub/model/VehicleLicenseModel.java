package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleInsuranceDto;
import lk.ijse.driveHub.dto.VehicleLicenseDto;
import lk.ijse.driveHub.dto.tableDto.LicenseTableDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleLicenseModel {
    private static Connection connection;
    public VehicleLicenseDto saveLicense(VehicleLicenseDto vehicleLicenseDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO vehicleLicenseDetails VALUES (NULL,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,vehicleLicenseDto.getVehicleId());
        preparedStatement.setString(2, vehicleLicenseDto.getLicenseNumber());
        preparedStatement.setString(3, String.valueOf(vehicleLicenseDto.getIssueDate()));
        preparedStatement.setString(4, String.valueOf(vehicleLicenseDto.getExpiryDate()));
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                vehicleLicenseDto.setId(generatedId);
                return vehicleLicenseDto;
            }
        }
        return null;
    }

    public VehicleLicenseDto getVehicleLicenseDetails(int vehicleId) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehicleLicenseDetails WHERE vehicleId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,vehicleId);
        VehicleLicenseDto vehicleLicenseDto = new VehicleLicenseDto();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vehicleLicenseDto.setId(resultSet.getInt(1));
            vehicleLicenseDto.setVehicleId(resultSet.getInt(2));
            vehicleLicenseDto.setLicenseNumber(resultSet.getString(3));
            vehicleLicenseDto.setIssueDate(LocalDate.parse(resultSet.getString(4)));
            vehicleLicenseDto.setExpiryDate(LocalDate.parse(resultSet.getString(5)));
        }
        return vehicleLicenseDto;
    }

    public boolean updateLicenseDetails(VehicleLicenseDto vehicleLicenseDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE vehicleLicenseDetails SET " +
                "licenseNumber = ?," +
                " issueDate = ?," +
                " expiryDate = ? WHERE vehicleId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,vehicleLicenseDto.getLicenseNumber());
        preparedStatement.setString(2, String.valueOf(vehicleLicenseDto.getIssueDate()));
        preparedStatement.setString(3, String.valueOf(vehicleLicenseDto.getExpiryDate()));
        preparedStatement.setInt(4,vehicleLicenseDto.getVehicleId());

        boolean isUpdateLicense = preparedStatement.executeUpdate() > 0;
        return isUpdateLicense;

    }
    public boolean deleteVehicle(int vehicleId) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM vehicleLicenseDetails WHERE vehicleId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,vehicleId);

        boolean isVehicleLicenseDeleted = preparedStatement.executeUpdate() > 0;
        return isVehicleLicenseDeleted;
    }
    public List<LicenseTableDto> getVehicleTableDto() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehicleLicenseDetails";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<LicenseTableDto> licenseTableDto = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            licenseTableDto.add(
                    new LicenseTableDto(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    ));
        }
        return licenseTableDto;
    }
}


