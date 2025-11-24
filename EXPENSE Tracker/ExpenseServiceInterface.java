package service;

import java.util.List;
import model.Expense;

/**
 * Demonstrates polymorphism: interface for Expense service.
 */
public interface ExpenseServiceInterface {
    void addExpense(Expense expense);
    List<Expense> fetchAllExpenses();
}
