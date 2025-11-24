package ui;

import dao.ExpenseDAO;
import model.Expense;
import util.UIStyles;

import javax.swing.*;
import java.awt.*;

public class AddExpenseFrame extends JFrame {

    public AddExpenseFrame() {
        setTitle("Add Expense");
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6,2,10,10));
        panel.setBackground(UIStyles.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel amtLbl = new JLabel("Amount:");
        JTextField amtField = new JTextField();

        JLabel catLbl = new JLabel("Category:");
        JComboBox<String> catBox = new JComboBox<>(new String[]{"Food","Travel","Shopping","Bills","Others"});

        JLabel dateLbl = new JLabel("Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField();

        JLabel descLbl = new JLabel("Description:");
        JTextField descField = new JTextField();

        JButton saveBtn = new JButton("Save");
        UIStyles.styleButton(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amtField.getText());
                String category = (String) catBox.getSelectedItem();
                String date = dateField.getText();
                String desc = descField.getText();

                ExpenseDAO dao = new ExpenseDAO();
                boolean ok = dao.addExpense(new Expense(amount, category, date, desc));

                if (ok) {
                    JOptionPane.showMessageDialog(this, "Expense added!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        panel.add(amtLbl); panel.add(amtField);
        panel.add(catLbl); panel.add(catBox);
        panel.add(dateLbl); panel.add(dateField);
        panel.add(descLbl); panel.add(descField);
        panel.add(new JLabel("")); panel.add(saveBtn);

        add(panel);
        setVisible(true);
    }
}
