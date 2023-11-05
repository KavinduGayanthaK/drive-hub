package lk.ijse.driveHub.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static DbConnection dbConnection;
    private final static String URL = "jdbc:mysql://localhost:3306/DriveHub";
    private final static Properties props =new Properties();
    private Connection connection;
    static {
        props.setProperty("user","root");
        props.setProperty("password","ijse@1234");
    }

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(URL,props);

    }
    public static DbConnection getInstance() throws SQLException {
            return(dbConnection == null) ? dbConnection = new DbConnection() :dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
