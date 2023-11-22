package lk.ijse.driveHub.model;

import com.sun.source.tree.BreakTree;
import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    static Connection connection = null;
    public boolean loginUser(UserDto userDto)  {
        try {
            connection = DbConnection.getInstance().getConnection();
            String sql = "SELECT * FROM users WHERE password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userDto.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            String userName1 = null;
            String password1 = null;
            while (resultSet.next()) {
               userName1 =  resultSet.getString(2);
                password1 = resultSet.getString(3);
                break;
            }

            if (userName1.equals(userDto.getUserName()) &&  password1.equals(userDto.getPassword())) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserDto> loadAllUser() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<UserDto> userDtoList = new ArrayList<>();
        while (resultSet.next()) {
            userDtoList.add(
                    new UserDto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    ));
        }
        return userDtoList;
    }

    public boolean updateUserPassword(UserDto userDto) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE users SET password = ? WHERE userName = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,userDto.getPassword());
        preparedStatement.setString(2,userDto.getUserName());

        boolean isUpdate = preparedStatement.executeUpdate() > 0;
        return isUpdate;

    }

    public boolean updateUserName(UserDto userDto,String newUserName) throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE users SET userName = ? WHERE userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,newUserName);
        preparedStatement.setString(2,userDto.getUserName());

        boolean isUpdate = preparedStatement.executeUpdate() > 0;
        return isUpdate;
    }
}
