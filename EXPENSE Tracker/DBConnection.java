package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles database connection using JDBC.
 * Follows Singleton pattern to ensure single DB connection instance.
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker";
    private static final String USER = "root";       // your MySQL username
    private static final String PASSWORD = "root";   // your MySQL password
    private static Connection connection = null;

    private DBConnection() {} // private constructor for Singleton

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database Connected Successfully!");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("❌ Database Connection Failed: " + e.getMessage());
            }
        }
        return connection;
    }
}
