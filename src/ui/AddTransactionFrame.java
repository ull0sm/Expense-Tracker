package src.ui;

import javax.swing.*;
import src.dao.CategoryDAO;
import src.dao.TransactionDAO;
import src.model.Transaction;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

public class AddTransactionFrame extends JFrame {
    private JComboBox<String> typeBox;
    private JTextField amountField;
    private JComboBox<String> categoryBox;
    private JTextField dateField;
    private JTextArea descArea;
    private JLabel errorLabel;

    public AddTransactionFrame() {
        setTitle("Add Transaction");
        setSize(400, 500);
        setLayout(new BorderLayout());
        
        // Initialize components
        typeBox = new JComboBox<>(new String[]{"income", "expense"});
        amountField = new JTextField();
        categoryBox = new JComboBox<>();
        dateField = new JTextField();
        descArea = new JTextArea(3, 20);
        errorLabel = new JLabel("", JLabel.CENTER);
        errorLabel.setForeground(Color.RED);
        
        // Add listener to update categories when type changes
        typeBox.addActionListener(e -> loadCategories());
        
        // Load categories based on initial selection
        loadCategories();
        
        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        formPanel.add(new JLabel("Type:"));
        formPanel.add(typeBox);
        formPanel.add(new JLabel("Amount:"));
        formPanel.add(amountField);
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryBox);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descArea));

        // Create button panel
        JPanel buttonPanel = new JPanel();
        JButton submitBtn = new JButton("Submit");
        JButton cancelBtn = new JButton("Cancel");
        
        submitBtn.addActionListener(e -> handleSubmit());
        cancelBtn.addActionListener(e -> dispose());
        
        buttonPanel.add(submitBtn);
        buttonPanel.add(cancelBtn);
        
        // Add components to frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(errorLabel, BorderLayout.NORTH);
        
        setVisible(true);
    }

    private void loadCategories() {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            String selectedType = (String) typeBox.getSelectedItem();
            if (selectedType == null) {
                showError("Please select a transaction type first");
                return;
            }
            
            List<String> categories = categoryDAO.getCategoriesByType(selectedType);
            categoryBox.setModel(new DefaultComboBoxModel<>(categories.toArray(new String[0])));
            
        } catch (Exception e) {
            showError("Error loading categories: " + e.getMessage());
            throw new RuntimeException("Failed to load categories", e);
        }
    }

    private void handleSubmit() {
        try {
            // Validate inputs
            if (!validateInputs()) {
                return;
            }

            // Create transaction
            Transaction t = new Transaction();
            t.setType((String) typeBox.getSelectedItem());
            t.setAmount(Double.parseDouble(amountField.getText()));
            t.setCategory((String) categoryBox.getSelectedItem());
            t.setDate(Date.valueOf(dateField.getText()));
            t.setDescription(descArea.getText());

            // Save transaction
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.addTransaction(t);
            
            // Show success message and close
            JOptionPane.showMessageDialog(this, "Transaction added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            showError("Error saving transaction: " + e.getMessage());
        }
    }

    private boolean validateInputs() {
        errorLabel.setText("");
        
        // Validate amount
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                showError("Amount must be greater than 0");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid amount");
            return false;
        }

        // Validate date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dateField.getText());
        } catch (ParseException e) {
            showError("Please enter a valid date (YYYY-MM-DD)");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        errorLabel.setText(message);
    }
}