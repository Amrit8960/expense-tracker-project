package dao;

import model.Expense;
import java.sql.*;
import java.util.ArrayList;

public class ExpenseDAO {

    // INSERT
    public boolean addExpense(Expense e) {
        String q = "INSERT INTO expenses(amount, category, date, description) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setDouble(1, e.getAmount());
            ps.setString(2, e.getCategory());
            ps.setString(3, e.getDate());
            ps.setString(4, e.getDescription());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // FETCH ALL
    public ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> list = new ArrayList<>();
        String q = "SELECT * FROM expenses";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(q)) {

            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("date"),
                        rs.getString("description")
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    // UPDATE
    public boolean updateExpense(Expense e) {
        String q = "UPDATE expenses SET amount=?, category=?, date=?, description=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setDouble(1, e.getAmount());
            ps.setString(2, e.getCategory());
            ps.setString(3, e.getDate());
            ps.setString(4, e.getDescription());
            ps.setInt(5, e.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteExpense(int id) {
        String q = "DELETE FROM expenses WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
