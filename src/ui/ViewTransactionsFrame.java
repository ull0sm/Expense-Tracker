package src.ui;

import javax.swing.*;
import src.dao.TransactionDAO;
import src.model.Transaction;
import java.util.*;
import java.awt.Color;
import java.text.SimpleDateFormat;

public class ViewTransactionsFrame extends JFrame {

    public ViewTransactionsFrame() {
        setTitle("All Transactions");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);  // center the frame on screen

        String[] cols = {"Date", "Type", "Category", "Amount", "Description"};
        List<Transaction> list = new TransactionDAO().getAllTransactions();
        String[][] data = new String[list.size()][5];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < list.size(); i++) {
            Transaction t = list.get(i);
            data[i][0] = (t.getDate() != null) ? sdf.format(t.getDate()) : "";
            data[i][1] = t.getType();
            data[i][2] = t.getCategory();
            data[i][3] = String.valueOf(t.getAmount());
            data[i][4] = t.getDescription();
        }

        JTable table = new JTable(data, cols);
        table.setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        add(new JScrollPane(table));
        setVisible(true);
    }

    // You can add a main method here for testing if needed
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewTransactionsFrame());
    }
}