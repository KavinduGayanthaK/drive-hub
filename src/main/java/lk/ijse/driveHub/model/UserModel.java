package lk.ijse.driveHub.model;

import com.sun.source.tree.BreakTree;
import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
