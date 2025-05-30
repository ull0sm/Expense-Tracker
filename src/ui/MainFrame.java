package src.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Personal Expense Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addBtn = new JButton("Add Transaction");
        JButton viewBtn = new JButton("View Transactions");
        JButton balanceBtn = new JButton("Show Balance");

        addBtn.addActionListener(e -> new AddTransactionFrame());
        viewBtn.addActionListener(e -> new ViewTransactionsFrame());
        balanceBtn.addActionListener(e -> new BalanceFrame());

        setLayout(new GridLayout(3, 1));
        add(addBtn);
        add(viewBtn);
        add(balanceBtn);

        setVisible(true);
    }
}