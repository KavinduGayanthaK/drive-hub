package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    private Connection connection;
    public CustomerDto saveCustomer(CustomerDto customerDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,customerDto.getId());
        preparedStatement.setString(2,customerDto.getFirstName());
        preparedStatement.setString(3,customerDto.getLastName());
        preparedStatement.setString(4,customerDto.getAddress());
        preparedStatement.setString(5,customerDto.getNumber());
        preparedStatement.setString(6,customerDto.getNic());
        preparedStatement.setString(7,customerDto.getEmail());
        preparedStatement.setString(8,customerDto.getIsUtilityBillSoftCopy());
        preparedStatement.setString(9,customerDto.getIsNicSoftCopy());
        preparedStatement.setString(10,customerDto.getStatus());

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                customerDto.setId(generatedId);

                return customerDto;
            }
        }
        return null;
    }

    public List<CustomerDto> getAllCustomer() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<CustomerDto> customerDto = new ArrayList<>();

        while (resultSet.next()) {
            customerDto.add(
                    new CustomerDto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            resultSet.getString(10)
                    ));
        }
        return customerDto;
    }

    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE customer SET " +
                "firstName = ?," +
                "lastName = ? ," +
                "address = ?, " +
                "number = ?," +
                "nic = ?," +
                "email = ?," +
                "isUtilityBillSoftCopy = ?, " +
                "isNicSoftCopy = ?" +
                " WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,customerDto.getFirstName());
        preparedStatement.setString(2,customerDto.getLastName());
        preparedStatement.setString(3,customerDto.getAddress());
        preparedStatement.setString(4,customerDto.getNumber());
        preparedStatement.setString(5,customerDto.getNic());
        preparedStatement.setString(6,customerDto.getEmail());
        preparedStatement.setString(7,customerDto.getIsUtilityBillSoftCopy());
        preparedStatement.setString(8,customerDto.getIsNicSoftCopy());
        preparedStatement.setInt(9,customerDto.getId());


        return preparedStatement.executeUpdate() > 0;
    }
    public CustomerDto searchCustomer(int id) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        CustomerDto customerDto = new CustomerDto();

        while (resultSet.next()) {
            customerDto.setFirstName(resultSet.getString(2));
            customerDto.setLastName(resultSet.getString(3));
            customerDto.setAddress(resultSet.getString(4));
            customerDto.setNumber(resultSet.getString(5));
            customerDto.setNic(resultSet.getString(6));
            customerDto.setEmail(resultSet.getString(7));
            customerDto.setIsUtilityBillSoftCopy(resultSet.getString(8));
            customerDto.setIsNicSoftCopy(resultSet.getString(9));
        }
        return customerDto;
    }


    public List<CustomerDto> searchCustomerTable(String nic) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE nic = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,nic);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<CustomerDto> customerDto = new ArrayList<>();

        while (resultSet.next()) {
            customerDto.add(
                    new CustomerDto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            resultSet.getString(10)
                    ));
        }
        return customerDto;
    }

    public boolean changeStatus(int id,String status) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE customer SET status = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, status);
        preparedStatement.setInt(2,id);

        boolean isStatus =  preparedStatement.executeUpdate() > 0;
        return isStatus;
    }

    public int customerCount() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS numberOfCustomer FROM customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = 0;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }

}
