package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.ReservationDto;
import lk.ijse.driveHub.dto.tableDto.CompleteReservationTableDto;
import lk.ijse.driveHub.dto.tableDto.ReservationCalenderDto;
import lk.ijse.driveHub.dto.tableDto.ReservationTableDto;
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
        String sql = "INSERT INTO reservation VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,reservationDto.getId());
        preparedStatement.setInt(2,reservationDto.getVehicleId());
        preparedStatement.setInt(3,reservationDto.getCustomerId());
        preparedStatement.setString(4, String.valueOf(reservationDto.getStartDate()));
        preparedStatement.setString(5, String.valueOf(reservationDto.getEndDate()));
        preparedStatement.setString(6,reservationDto.getStatus());

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

    public int availableVehicle() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS numberOfVehiclesNotInReservation\n" +
                "FROM vehicle\n" +
                "         LEFT JOIN reservation ON vehicle.id = reservation.vehicleId\n" +
                "WHERE reservation.id IS NULL OR (reservation.startDate > CURDATE() OR reservation.endDate < CURDATE())";
        int count = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }

    public boolean deleteReservation(int id) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM reservation WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        boolean isDeleted = preparedStatement.executeUpdate() > 0;
        return isDeleted;
    }

    public boolean statusUpdate(int id,String complete) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE reservation SET status = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,complete);
        preparedStatement.setInt(2,id);

        boolean isStatusUpdate = preparedStatement.executeUpdate() > 0;
        return isStatusUpdate;
    }

    public List<CompleteReservationTableDto> getCompleteReservation() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT\n" +
                "    r.id AS reservationId,\n" +
                "    r.vehicleId,\n" +
                "    v.model AS vehicleModel,\n" +
                "    v.registeredNumber,\n" +
                "    CONCAT(c.firstName, ' ', c.lastName) AS customerName,\n" +
                "    r.startDate AS reservationDate,\n" +
                "    r.endDate AS returnDate\n" +
                "FROM\n" +
                "    reservation r\n" +
                "        JOIN\n" +
                "    vehicle v ON r.vehicleId = v.id\n" +
                "        JOIN\n" +
                "    customer c ON r.customerId = c.id\n" +
                "WHERE\n" +
                "        r.status = 'Completed'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<CompleteReservationTableDto> reservationTableDtos = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            reservationTableDtos.add(
                    new CompleteReservationTableDto(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            "Completed"
                    )
            );
        }
        return reservationTableDtos;
    }

    public List<ReservationCalenderDto> getReservation() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT\n" +
                "    r.status AS reservationStatus,\n" +
                "    v.registeredNumber,\n" +
                "    r.startDate,\n" +
                "    r.endDate\n" +
                "FROM\n" +
                "    reservation r\n" +
                "        JOIN\n" +
                "    vehicle v ON r.vehicleId = v.id\n" +
                "WHERE\n" +
                "        r.status = 'On Renting'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<ReservationCalenderDto> reservationCalenderDtos = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            reservationCalenderDtos.add(
                    new ReservationCalenderDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return reservationCalenderDtos;
    }

    public List<ReservationTableDto> getOnRentingReservation() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT\n" +
                "    r.id AS reservationId,\n" +
                "    r.vehicleId,\n" +
                "    v.model AS vehicleModel,\n" +
                "    v.registeredNumber,\n" +
                "    CONCAT(c.firstName, ' ', c.lastName) AS customerName,\n" +
                "    r.startDate AS reservationDate,\n" +
                "    r.endDate AS returnDate\n" +
                "FROM\n" +
                "    reservation r\n" +
                "        JOIN\n" +
                "    vehicle v ON r.vehicleId = v.id\n" +
                "        JOIN\n" +
                "    customer c ON r.customerId = c.id\n" +
                "WHERE\n" +
                "        r.status = 'On Renting'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<ReservationTableDto> reservationTableDtos = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            reservationTableDtos.add(
                    new ReservationTableDto(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            "On Renting"
                    )
            );
        }
        return reservationTableDtos;
    }
}
