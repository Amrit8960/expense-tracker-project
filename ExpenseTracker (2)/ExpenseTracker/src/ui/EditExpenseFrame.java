package ui;

import dao.ExpenseDAO;
import model.Expense;
import util.UIStyles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditExpenseFrame extends JFrame {

    private JComboBox<String> selectBox;
    private JTextField amtField, dateField, descField;
    private JComboBox<String> catBox;
    private ArrayList<Expense> expenses;

    public EditExpenseFrame() {
        setTitle("Edit Expense");
        setSize(460, 350);
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(6, 2, 10, 10));
        p.setBackground(UIStyles.BG);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ExpenseDAO dao = new ExpenseDAO();
        expenses = dao.getAllExpenses();

        // Dropdown menu items
        String[] items = new String[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            Expense e = expenses.get(i);
            items[i] = e.getId() + " | " + e.getCategory() + " | ₹" + e.getAmount();
        }

        // GUI Components
        selectBox = new JComboBox<>(items);
        amtField = new JTextField();
        catBox = new JComboBox<>(new String[]{"Food", "Travel", "Shopping", "Bills", "Others"});
        dateField = new JTextField();
        descField = new JTextField();

        JButton loadBtn = new JButton("Load Expense");
        UIStyles.styleButton(loadBtn);

        JButton saveBtn = new JButton("Save Changes");
        UIStyles.styleButton(saveBtn);

        // Add components to panel
        p.add(new JLabel("Select Expense:"));
        p.add(selectBox);

        p.add(new JLabel("Amount:"));
        p.add(amtField);

        p.add(new JLabel("Category:"));
        p.add(catBox);

        p.add(new JLabel("Date (YYYY-MM-DD):"));
        p.add(dateField);

        p.add(new JLabel("Description:"));
        p.add(descField);

        p.add(loadBtn);
        p.add(saveBtn);

        loadBtn.addActionListener(e -> loadSelectedExpense());
        saveBtn.addActionListener(e -> saveChanges());

        add(p);
        setVisible(true);
    }

    // Load selected expense values into form fields
    private void loadSelectedExpense() {
        int idx = selectBox.getSelectedIndex();
        if (idx < 0) return;

        Expense e = expenses.get(idx);
        amtField.setText(String.valueOf(e.getAmount()));
        catBox.setSelectedItem(e.getCategory());
        dateField.setText(e.getDate());
        descField.setText(e.getDescription());
    }

    // Save changes to database
    private void saveChanges() {
        try {
            int idx = selectBox.getSelectedIndex();
            if (idx < 0) return;

            Expense old = expenses.get(idx);

            double amount = Double.parseDouble(amtField.getText());
            String category = (String) catBox.getSelectedItem();
            String date = dateField.getText();
            String desc = descField.getText();

            Expense updated = new Expense(old.getId(), amount, category, date, desc);

            ExpenseDAO dao = new ExpenseDAO();
            boolean ok = dao.updateExpense(updated);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Expense Updated Successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Update Failed!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
}
