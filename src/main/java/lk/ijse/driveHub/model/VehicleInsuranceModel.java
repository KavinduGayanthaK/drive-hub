package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleInsuranceDto;
import lk.ijse.driveHub.dto.tableDto.InsuranceTableDto;
import lk.ijse.driveHub.dto.tableDto.LicenseTableDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public VehicleInsuranceDto getVehicleInsuranceDetails(int vehicleId) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehicleInsuranceDetails WHERE vehicleId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, vehicleId);

        ResultSet resultSet = preparedStatement.executeQuery();

        VehicleInsuranceDto vehicleInsuranceDtoList = new VehicleInsuranceDto();
        while (resultSet.next()) {
            vehicleInsuranceDtoList.setId(resultSet.getInt(1));
            vehicleInsuranceDtoList.setVehicleId(resultSet.getInt(2));
            vehicleInsuranceDtoList.setInsuranceNumber(resultSet.getString(3));
            vehicleInsuranceDtoList.setIssueDate(LocalDate.parse(resultSet.getString(4)));
            vehicleInsuranceDtoList.setExpiryDate(LocalDate.parse(resultSet.getString(5)));
        }
        return vehicleInsuranceDtoList;
    }

    public boolean updateInsuranceDetails(VehicleInsuranceDto vehicleInsuranceDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE vehicleInsuranceDetails SET " +
                "insuranceNumber = ?," +
                " issueDate = ?," +
                " expiryDate = ? WHERE vehicleId = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, vehicleInsuranceDto.getInsuranceNumber());
        preparedStatement.setString(2, String.valueOf(vehicleInsuranceDto.getIssueDate()));
        preparedStatement.setString(3, String.valueOf(vehicleInsuranceDto.getExpiryDate()));
        preparedStatement.setInt(4, vehicleInsuranceDto.getVehicleId());

        boolean isUpdateInsurance = preparedStatement.executeUpdate() > 0;
        return isUpdateInsurance;
    }

    public boolean deleteVehicle(int vehicleId) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM\n" +
                "\n" +
                "}\n vehicleInsuranceDetails WHERE vehicleId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, vehicleId);

        boolean isVehicleInsuranceDeleted = preparedStatement.executeUpdate() > 0;
        return isVehicleInsuranceDeleted;
    }
    public List<InsuranceTableDto> getVehicleTableDto() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehicleInsuranceDetails";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<InsuranceTableDto> insuranceTableDtos = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            insuranceTableDtos.add(
                    new InsuranceTableDto(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    ));
        }
        return insuranceTableDtos;
    }
}


