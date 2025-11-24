package dao;

import database.DBConnection;
import model.Expense;

import java.sql.*;
import java.util.*;

/**
 * Handles all Expense database operations.
 * Demonstrates use of List, Set, and Map collections.
 */
public class ExpenseDAO implements GenericDAO<Expense> {

    // Stores all unique categories (Set)
    private Set<String> categories = new HashSet<>();

    @Override
    public void insert(Expense expense) {
        String query = "INSERT INTO expenses (title, amount, category, date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, expense.getTitle());
            stmt.setDouble(2, expense.getAmount());
            stmt.setString(3, expense.getCategory());
            stmt.setString(4, expense.getDate());
            stmt.executeUpdate();

            categories.add(expense.getCategory()); // Add to Set

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Expense> getAll() {
        List<Expense> list = new ArrayList<>();
        String query = "SELECT * FROM expenses";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("date")
                );
                list.add(expense);
                categories.add(expense.getCategory());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /** Returns unique categories as a Set */
    public Set<String> getAllCategories() {
        return categories;
    }

    /** Returns expenses grouped by category using Map */
    public Map<String, List<Expense>> getExpensesByCategory() {
        Map<String, List<Expense>> grouped = new HashMap<>();
        List<Expense> allExpenses = getAll();

        for (Expense e : allExpenses) {
            grouped.computeIfAbsent(e.getCategory(), k -> new ArrayList<>()).add(e);
        }

        return grouped;
    }
}
