package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {

    Connection connection;

    public PaymentDto savePayment(PaymentDto paymentDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO payment VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,paymentDto.getId());
        preparedStatement.setInt(2,paymentDto.getReservationId());
        preparedStatement.setDouble(3,paymentDto.getDepositAmount());
        preparedStatement.setDouble(4,paymentDto.getAmount());
        preparedStatement.setString(5,paymentDto.getType());
        preparedStatement.setString(6, String.valueOf(paymentDto.getAbout()));
        preparedStatement.setString(7, String.valueOf(paymentDto.getDate()));

        int affectedRow = preparedStatement.executeUpdate();
        if (affectedRow > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generateId = generatedKeys.getInt(1);
                paymentDto.setId(generateId);
                return paymentDto;
            }
        }
        return null;
    }
}
