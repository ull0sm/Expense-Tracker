package src.ui;

import javax.swing.*;
import src.dao.TransactionDAO;

public class BalanceFrame extends JFrame {
    public BalanceFrame() {
        setTitle("Current Balance");
        setSize(300, 100);

        double balance = new TransactionDAO().getBalance();
        JLabel label = new JLabel("Your current balance is: ₹" + balance);
        add(label);

        setVisible(true);
    }
}