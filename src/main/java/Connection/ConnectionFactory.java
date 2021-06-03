package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class makes the connection to the database
 */
public class ConnectionFactory {
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    /**
     * Driver
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * Data base URL
     */
    private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb";
    /**
     * User
     */
    private static final String USER = "root";
    /**
     * Password
     */
    private static final String PASS = "root";
    /**
     * Connection Factory
     */
    private static ConnectionFactory singleInstance = new ConnectionFactory();
    /**
     * Class constructor
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create connection
     * @return New connection to data base
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Get connection
     * @return The connection with data base
     */
    public  static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * Close the connection
     * @param connection Connection with data base
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /**
     * Close the connection if the Statement is null
     * @param statement Statement
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * Close the connection if the ResultSet is null
     * @param resultSet ResultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
