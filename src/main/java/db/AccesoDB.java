package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {
    private final String driver;
    private final String url;

    public AccesoDB() {
        driver ="org.sqlite.JDBC";
        url = "jdbc:sqlite:db/login.db";
    }

    public Connection getConexion() throws  ClassNotFoundException, SQLException {
        Class.forName(driver);

        return DriverManager.getConnection(url);
    }

}
