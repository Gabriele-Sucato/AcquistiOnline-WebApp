package main.java.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Properties;


public class DatabaseConfig {

    private static final String PROPERTIES_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Configuration file '" + PROPERTIES_FILE + "' not found!"+" path: "+DatabaseConfig.class.getResource(PROPERTIES_FILE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJdbcUrl() {
        return properties.getProperty("jdbc.url");
    }

    public static String getJdbcUser() {
        return properties.getProperty("jdbc.user");
    }

    public static String getJdbcPassword() {
        return properties.getProperty("jdbc.password");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());
    }
}
