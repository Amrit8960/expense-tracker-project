package ui;

import dao.ExpenseDAO;
import model.Expense;
import util.UIStyles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeleteExpenseFrame extends JFrame {

    public DeleteExpenseFrame() {
        setTitle("Delete Expense");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3,1,10,10));
        panel.setBackground(UIStyles.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        ExpenseDAO dao = new ExpenseDAO();
        ArrayList<Expense> list = dao.getAllExpenses();

        String[] items = new String[list.size()];
        for (int i=0; i<list.size(); i++) {
            Expense e = list.get(i);
            items[i] = e.getId() + " | " + e.getCategory() + " | " + e.getAmount();
        }

        JComboBox<String> box = new JComboBox<>(items);
        JButton deleteBtn = new JButton("Delete");
        UIStyles.styleButton(deleteBtn);

        deleteBtn.addActionListener(e -> {
            int idx = box.getSelectedIndex();
            if (idx >= 0) {
                int id = list.get(idx).getId();
                if (dao.deleteExpense(id)) {
                    JOptionPane.showMessageDialog(this, "Deleted");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed");
                }
            }
        });

        panel.add(box);
        panel.add(deleteBtn);
        add(panel);
        setVisible(true);
    }
}
