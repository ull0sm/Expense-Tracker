package src.dao;

import src.db.DBConnection;
import src.model.Transaction;
import java.sql.*;
import java.util.*;

public class TransactionDAO {
    public void addTransaction(Transaction txn) {
        String sql = "INSERT INTO transactions (type, amount, category, description, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, txn.getType());
            stmt.setDouble(2, txn.getAmount());
            stmt.setString(3, txn.getCategory());
            stmt.setString(4, txn.getDescription());
            stmt.setDate(5, new java.sql.Date(txn.getDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY date DESC";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setType(rs.getString("type"));
                t.setAmount(rs.getDouble("amount"));
                t.setCategory(rs.getString("category"));
                t.setDescription(rs.getString("description"));
                t.setDate(rs.getDate("date")); // java.sql.Date extends java.util.Date
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public double getBalance() {
        String sql = "SELECT SUM(CASE WHEN type='income' THEN amount ELSE -amount END) AS balance FROM transactions";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next())
                return rs.getDouble("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
