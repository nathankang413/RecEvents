package backend;

import java.sql.*;
import java.util.Properties;

public class SqlConnector {

    private static Connection connection;

    /**
     * Constructor for SqlConnector. Initializes connection
     * to our database.
     */
    public static void init() {
        Properties creds = new Properties();
        creds.put("user", Constants.DB_USERNAME);
        creds.put("password", Constants.DB_PASSWORD);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't initialize mySQL driver: " + e);
        }

        try {
            connection = DriverManager.getConnection(Constants.DB_URL, creds);
        } catch (Exception e) {
            System.out.println("Couldn't connect to database: " + e);
        }
    }

    public static ResultSet runSql(String query) {
        if (connection == null) {
            System.out.println("SqlConnector not initialized.");
            return null;
        }

        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
