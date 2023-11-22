package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.ReservationDto;
import lk.ijse.driveHub.dto.tableDto.ReservationVehicleTableDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationModel {

    Connection connection;
    public List<ReservationVehicleTableDto> searchVehicle(ReservationDto reservationDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT\n" +
                "    v.id AS vehicle_id,\n" +
                "    vt.name AS vehicle_type,\n" +
                "    v.brand, \n" +
                "    v.model,\n" +
                "    v.perDayRate,\n" +
                "    v.perAdditionalKmRate\n" +
                "FROM\n" +
                "    vehicle v\n" +
                "        JOIN\n" +
                "    vehicleType vt ON v.vehicleTypeId = vt.id\n" +
                "        LEFT JOIN\n" +
                "    reservation r ON v.id = r.vehicleId\n" +
                "        AND (\n" +
                "                             (? BETWEEN r.startDate AND r.endDate)\n" +
                "                             OR (? BETWEEN r.startDate AND r.endDate)\n" +
                "                             OR (r.startDate BETWEEN ? AND ?)\n" +
                "                         )\n" +
                "WHERE\n" +
                "    r.id IS NULL\n" +
                "   OR r.startDate IS NULL";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(reservationDto.getStartDate()));
        preparedStatement.setString(2, String.valueOf(reservationDto.getEndDate()));
        preparedStatement.setString(3, String.valueOf(reservationDto.getStartDate()));
        preparedStatement.setString(4, String.valueOf(reservationDto.getEndDate()));

        ResultSet resultSet = preparedStatement.executeQuery();
        List<ReservationVehicleTableDto> reservationVehicleTableDtoList = new ArrayList<>();
        while (resultSet.next()) {
            reservationVehicleTableDtoList.add(
                    new ReservationVehicleTableDto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDouble(5),
                            resultSet.getDouble(6)
                    ));
        }
        return reservationVehicleTableDtoList;
    }

    public ReservationDto saveReservation(ReservationDto reservationDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO reservation VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,reservationDto.getId());
        preparedStatement.setInt(2,reservationDto.getVehicleId());
        preparedStatement.setInt(3,reservationDto.getCustomerId());
        preparedStatement.setString(4, String.valueOf(reservationDto.getStartDate()));
        preparedStatement.setString(5, String.valueOf(reservationDto.getEndDate()));

        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generateId = generatedKeys.getInt(1);
                reservationDto.setId(generateId);
                return reservationDto;
            }
        }
        return null;
    }

    public ReservationDto getAllReservation() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM reservation";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ReservationDto reservationDto = new ReservationDto();

        while (resultSet.next()) {
            reservationDto.setId(resultSet.getInt(1));
            reservationDto.setVehicleId(resultSet.getInt(2));
            reservationDto.setCustomerId(resultSet.getInt(3));
            reservationDto.setStartDate(LocalDate.parse(resultSet.getString(4)));
            reservationDto.setEndDate(LocalDate.parse(resultSet.getString(5)));

        }
        return reservationDto;
    }

}
