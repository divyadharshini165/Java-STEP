// Assignment 1: Personal Finance Manager
// Topic: Basic Class Creation and Object Usage

import java.util.*;

class PersonalAccount {
    // Instance variables (unique per account)
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    // Static variables (shared across all accounts)
    private static int totalAccounts = 0;
    private static String bankName = "Default Bank";

    // Constructor
    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        totalAccounts++;
    }

    // Instance Methods
    public void addIncome(double amount, String description) {
        if (amount > 0) {
            currentBalance += amount;
            totalIncome += amount;
            System.out.println(accountHolderName + " received income: " + amount + " (" + description + ")");
        } else {
            System.out.println("Invalid income amount!");
        }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println(accountHolderName + " spent: " + amount + " (" + description + ")");
        } else {
            System.out.println("Invalid or insufficient balance for expense!");
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\n===== Account Summary =====");
        System.out.println("Bank Name      : " + bankName);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income   : " + totalIncome);
        System.out.println("Total Expenses : " + totalExpenses);
        System.out.println("Total Savings  : " + calculateSavings());
        System.out.println("============================\n");
    }

    // Static Methods
    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static String generateAccountNumber() {
        return "ACCT" + (1000 + totalAccounts + 1);
    }
}

// Main class
public class FinanceManager {
    public static void main(String[] args) {
        // Set bank name (shared across all accounts)
        PersonalAccount.setBankName("FutureBank Ltd");

        // Create 3 accounts
        PersonalAccount acc1 = new PersonalAccount("Alice");
        PersonalAccount acc2 = new PersonalAccount("Bob");
        PersonalAccount acc3 = new PersonalAccount("Charlie");

        // Perform transactions
        acc1.addIncome(5000, "Salary");
        acc1.addExpense(1200, "Groceries");
        acc1.addExpense(800, "Electricity Bill");

        acc2.addIncome(7000, "Freelance Project");
        acc2.addExpense(2000, "Rent");
        acc2.addExpense(500, "Internet");

        acc3.addIncome(3000, "Part-time Job");
        acc3.addExpense(1000, "Shopping");
        acc3.addIncome(1500, "Bonus");

        // Display summaries
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Show total accounts created
        System.out.println("Total Accounts Created: " + PersonalAccount.getTotalAccounts());

        // Demonstrate static vs instance variable
        System.out.println("Bank Name shared across all accounts: " + "FutureBank Ltd");
        System.out.println("Unique Balances: Alice = " + acc1.calculateSavings() +
                           ", Bob = " + acc2.calculateSavings() +
                           ", Charlie = " + acc3.calculateSavings());
    }
}
