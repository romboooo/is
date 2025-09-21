package storage;

import core.Dragon;
import conf.DBConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseProcessor {
    Connection connection;
    public DatabaseProcessor() {
        this.connection = connectToDB();
    }
    private ArrayList<Dragon> dragons;
    private static final String URL = DBConfig.getDbUrl();
    private static final String USER = DBConfig.getDbUser();
    private static final String PASSWORD = DBConfig.getDbPassword();

    public static Connection connectToDB() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

            // Устанавливаем соединение
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("Connected to PostgreSQL database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return connection;
    }
}
