package src.model;

import java.util.Date;

public class Transaction {
    private int id;
    private String type;
    private double amount;
    private String category;
    private String description;
    private Date date;

    // Default constructor
    public Transaction() {}

    // Parameterized constructor
    public Transaction(int id, String type, double amount, String category, String description, Date date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    // Getters
    public int getId() { return id; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public Date getDate() { return date; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setType(String type) {
        if (type != null && (type.equals("income") || type.equals("expense"))) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Type must be 'income' or 'expense'");
        }
    }
    public void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }
    public void setCategory(String category) {
        if (category != null && !category.trim().isEmpty()) {
            this.category = category.trim();
        } else {
            throw new IllegalArgumentException("Category cannot be empty");
        }
    }
    public void setDescription(String description) {
        this.description = description != null ? description.trim() : "";
    }
    public void setDate(Date date) {
        if (date != null) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }
}