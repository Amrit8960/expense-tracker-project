package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO for maintaining transaction logs.
 */
public class TransactionDAO {
    public void insertLog(Connection conn, String message) throws SQLException {
        String query = "INSERT INTO transaction_log (message) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, message);
            stmt.executeUpdate();
        }
    }
}
