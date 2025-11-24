package service;

import dao.ExpenseDAO;
import java.util.*;
import model.Expense;

/**
 * Implements the service interface and demonstrates usage of
 * collections: List, Set, Map with DAO.
 */
public class ExpenseServiceImpl implements ExpenseServiceInterface {
    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl() {
        expenseDAO = new ExpenseDAO();
    }

    @Override
    public void addExpense(Expense expense) {
        expenseDAO.insert(expense);
    }

    @Override
    public List<Expense> fetchAllExpenses() {
        return expenseDAO.getAll();
    }

    public Set<String> getUniqueCategories() {
        return expenseDAO.getAllCategories();
    }

    public Map<String, List<Expense>> getCategoryWiseExpenses() {
        return expenseDAO.getExpensesByCategory();
    }
}
