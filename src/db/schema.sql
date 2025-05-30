-- Create the database
CREATE DATABASE IF NOT EXISTS expense_tracker;
USE expense_tracker;

-- Drop tables if they already exist (for reset/debug purposes)
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS categories;

-- Create 'categories' table
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type ENUM('income', 'expense') NOT NULL
);

-- Insert default categories
INSERT INTO categories (name, type) VALUES
('Salary', 'income'),
('Freelance', 'income'),
('Investment Return', 'income'),
('Food', 'expense'),
('Transport', 'expense'),
('Shopping', 'expense'),
('Medical', 'expense');

-- Create 'transactions' table
CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type ENUM('income', 'expense') NOT NULL,
    amount DOUBLE NOT NULL,
    category VARCHAR(100) NOT NULL,
    description TEXT,
    date DATE NOT NULL
);