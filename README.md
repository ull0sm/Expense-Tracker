# Personal Expense Tracker

A Java-based desktop application for tracking personal expenses and income. This application helps users manage their finances by tracking transactions, categorizing them, and providing balance information.

## Features

- Add income and expense transactions
- View all transactions with detailed information
- Automatic balance calculation
- Categorization of transactions
- User-friendly GUI interface
- Data persistence using MySQL database

## Project Structure

```
src/
├── Main.java                # Application entry point
├── db/                     # Database related classes
│   └── DBConnection.java   # Database connection manager
├── dao/                    # Data Access Objects
│   ├── CategoryDAO.java    # Category data access
│   └── TransactionDAO.java # Transaction data access
├── model/                  # Data models
│   ├── Category.java       # Category model
│   └── Transaction.java    # Transaction model
└── ui/                     # User Interface components
    ├── AddTransactionFrame.java # Add transaction form
    ├── BalanceFrame.java    # Balance display window
    ├── MainFrame.java       # Main application window
    └── ViewTransactionsFrame.java # Transaction viewer
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- MySQL Server 5.7 or higher
- MySQL Connector/J (JDBC driver)

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/ull0sm/Expense-Tracker.git
```

2. Set up the database:
   - Create a MySQL database named `expense_tracker`
   - Import the schema from `src/db/schema.sql`
   - Update the database connection details in `src/db/DBConnection.java` if needed

3. Build and run the application:
   - Compile the Java files:
   ```bash
   javac src/*.java src/ui/*.java src/dao/*.java src/model/*.java src/db/*.java
   ```
   - Run the application:
   ```bash
   java src.Main
   ```

## Usage

1. Launch the application
2. Use the main window to:
   - Add new transactions
   - View all transactions
   - Check current balance

### Adding a Transaction
- Click "Add Transaction" button
- Select transaction type (income/expense)
- Enter the amount
- Choose a category
- Enter transaction date
- Add optional description
- Click "Submit" to save

### Viewing Transactions
- Click "View Transactions" button
- See all transactions in a table format
- Columns include: Date, Type, Category, Amount, Description

### Checking Balance
- Click "Show Balance" button
- View your current financial balance

## How It Works

The application uses a client-server architecture with:
- Java Swing for the GUI interface
- MySQL database for data persistence
- MVC (Model-View-Controller) pattern for code organization

Data flow:
1. User interacts with UI components
2. Controllers process user input
3. Data is stored in MySQL database
4. Balance is calculated automatically
5. Results are displayed in the UI

## Contribution

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Support

For support, please open an issue in the GitHub repository or contact the project maintainer.