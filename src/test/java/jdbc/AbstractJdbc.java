package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractJdbc {

    private static final String DB_URL = "jdbc:postgresql://10.1.0.229:5433/tuneverse_local";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "tuneverse";

    protected static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        DriverManager.setLoginTimeout(50);
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return connection;
    }
}
