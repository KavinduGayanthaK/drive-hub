package lk.ijse.driveHub.model;

import lk.ijse.driveHub.db.DbConnection;
import lk.ijse.driveHub.dto.VehicleTypeDto;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleTypeModel {
    public List<VehicleTypeDto> getAllVehicleType() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehicleType";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<VehicleTypeDto> vehicleType = new ArrayList<>();

        while (resultSet.next()) {
            vehicleType.add(
                    new VehicleTypeDto(
                            resultSet.getInt(1),
                            resultSet.getString(2)
                    ));
        }

        return vehicleType;
    }
}
