package service;

import dao.ExpenseDAO;
import dao.TransactionDAO;
import database.DBConnection;
import model.Expense;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Demonstrates Transaction Management in JDBC.
 * If one operation fails, both are rolled back.
 */
public class TransactionService {
    private ExpenseDAO expenseDAO = new ExpenseDAO();
    private TransactionDAO logDAO = new TransactionDAO();

    public void addExpenseWithTransaction(Expense expense) {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Step 1: Add expense
            expenseDAO.insertExpense(conn, expense);

            // Step 2: Add log message
            logDAO.insertLog(conn, "Added Expense: " + expense.getTitle() + " ₹" + expense.getAmount());

            // Simulate error (for testing rollback)
            // int x = 10 / 0;

            // Step 3: Commit all operations
            conn.commit();
            System.out.println("✅ Transaction committed successfully!");

        } catch (Exception e) {
            System.out.println("⚠️ Error occurred, rolling back transaction...");
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // Undo all changes
                    System.out.println("❌ Transaction rolled back!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
