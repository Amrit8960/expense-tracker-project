package ui;

import dao.ExpenseDAO;
import model.Expense;
import util.UIStyles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewExpensesFrame extends JFrame {

    public ViewExpensesFrame() {
        setTitle("View All Expenses");
        setSize(650, 350);
        setLocationRelativeTo(null);

        String[] cols = {"ID", "Amount", "Category", "Date", "Description"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        table.setFont(UIStyles.normalFont());
        table.setRowHeight(25);
        JScrollPane pane = new JScrollPane(table);

        ExpenseDAO dao = new ExpenseDAO();
        ArrayList<Expense> list = dao.getAllExpenses();

        for (Expense e : list) {
            model.addRow(new Object[]{
                    e.getId(),
                    e.getAmount(),
                    e.getCategory(),
                    e.getDate(),
                    e.getDescription()
            });
        }

        add(pane, BorderLayout.CENTER);
        setVisible(true);
    }
}
