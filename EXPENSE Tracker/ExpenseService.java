package service;

import dao.ExpenseDAO;
import model.Expense;
import java.util.List;

/**
 * Business logic layer between UI and DAO.
 */
public class ExpenseService {
    private ExpenseDAO dao = new ExpenseDAO();

    public void addExpense(Expense expense) {
        dao.addExpense(expense);
    }

    public List<Expense> getAllExpenses() {
        return dao.getAllExpenses();
    }

    public void updateExpense(Expense expense) {
        dao.updateExpense(expense);
    }

    public void deleteExpense(int id) {
        dao.deleteExpense(id);
    }
}

