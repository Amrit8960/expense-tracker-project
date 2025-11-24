package ui;

import javax.swing.*;
import java.awt.*;
import util.UIStyles;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Expense Tracker - Dashboard");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5,1,10,10));
        panel.setBackground(UIStyles.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        JLabel title = new JLabel("Expense Tracker", SwingConstants.CENTER);
        title.setFont(UIStyles.titleFont());

        JButton add = new JButton("Add Expense");
        JButton view = new JButton("View All Expenses");
        JButton edit = new JButton("Edit Expense");
        JButton delete = new JButton("Delete Expense");

        UIStyles.styleButton(add);
        UIStyles.styleButton(view);
        UIStyles.styleButton(edit);
        UIStyles.styleButton(delete);

        add.addActionListener(e -> new AddExpenseFrame());
        view.addActionListener(e -> new ViewExpensesFrame());
        edit.addActionListener(e -> new EditExpenseFrame());
        delete.addActionListener(e -> new DeleteExpenseFrame());

        panel.add(title);
        panel.add(add);
        panel.add(view);
        panel.add(edit);
        panel.add(delete);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainDashboard();
    }
}
