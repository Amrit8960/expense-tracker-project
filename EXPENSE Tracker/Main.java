import dao.ExpenseDAO;
import model.Expense;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ExpenseDAO dao = new ExpenseDAO();

        // 1️⃣ CREATE
        Expense newExpense = new Expense("Dinner", 250.00, "Food", "2025-11-24");
        dao.addExpense(newExpense);

        // 2️⃣ READ
        System.out.println("\n📋 All Expenses:");
        List<Expense> expenses = dao.getAllExpenses();
        for (Expense e : expenses) {
            System.out.println(e);
        }

        // 3️⃣ UPDATE
        Expense update = new Expense(1, "Lunch", 180.00, "Food", "2025-11-25");
        dao.updateExpense(update);

        // 4️⃣ DELETE
        dao.deleteExpense(2);
    }
}
