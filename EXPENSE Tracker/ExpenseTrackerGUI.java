package ui;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import model.Expense;
import service.ExpenseServiceImpl;

/**
 * GUI demonstrating integration of Lists, Sets, Maps.
 */
public class ExpenseTrackerGUI extends JFrame {
    private JTextField titleField, amountField, categoryField, dateField;
    private JTextArea outputArea;
    private ExpenseServiceImpl service;

    public ExpenseTrackerGUI() {
        service = new ExpenseServiceImpl();

        setTitle("💰 Expense Tracker (OOP + Collections)");
        setSize(550, 550);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField(); inputPanel.add(titleField);
        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField(); inputPanel.add(amountField);
        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField(); inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField(); inputPanel.add(dateField);

        JButton addBtn = new JButton("Add Expense");
        JButton viewBtn = new JButton("View All");
        JButton categoryBtn = new JButton("View By Category");
        inputPanel.add(addBtn);
        inputPanel.add(viewBtn);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(categoryBtn, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addExpense());
        viewBtn.addActionListener(e -> showAll());
        categoryBtn.addActionListener(e -> showByCategory());
    }

    private void addExpense() {
        try {
            String title = titleField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();
            String date = dateField.getText();

            service.addExpense(new Expense(title, amount, category, date));
            JOptionPane.showMessageDialog(this, "Expense Added Successfully!");
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    private void showAll() {
        List<Expense> list = service.fetchAllExpenses();
        outputArea.setText("🧾 All Expenses:\n");
        for (Expense exp : list) {
            outputArea.append(exp + "\n");
        }

        // Show Set usage
        Set<String> cats = service.getUniqueCategories();
        outputArea.append("\n📂 Unique Categories: " + cats + "\n");
    }

    private void showByCategory() {
        Map<String, List<Expense>> grouped = service.getCategoryWiseExpenses();
        outputArea.setText("📊 Expenses by Category:\n");
        for (String cat : grouped.keySet()) {
            outputArea.append("\nCategory: " + cat + "\n");
            for (Expense e : grouped.get(cat)) {
                outputArea.append("  " + e + "\n");
            }
        }
    }

    private void clearFields() {
        titleField.setText("");
        amountField.setText("");
        categoryField.setText("");
        dateField.setText("");
    }
}
