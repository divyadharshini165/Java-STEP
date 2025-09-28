public class BankAccount {
    // ---------- Static variables (shared by ALL accounts) ----------
    private static String bankName;
    private static int totalAccounts = 0;
    private static double interestRate;

    // ---------- Instance variables (unique for EACH account) ----------
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // ---------- Constructor ----------
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        totalAccounts++; // count every new account created
    }

    // ---------- Static methods ----------
    public static void setBankName(String name) {
        bankName = name;
    }

    public static void setInterestRate(double rate) {
        interestRate = rate;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static void displayBankInfo() {
        System.out.println("Bank: " + bankName + " | Total Accounts: " + totalAccounts + " | Interest Rate: " + interestRate);
    }

    // ---------- Instance methods ----------
    public void deposit(double amount) {
        balance += amount;
        System.out.println(accountHolder + " deposited " + amount + ". New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(accountHolder + " withdrew " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Insufficient balance for " + accountHolder);
        }
    }

    public double calculateInterest() {
        return balance * interestRate;
    }

    public void displayAccountInfo() {
        System.out.println("Account: " + accountNumber + " | Holder: " + accountHolder + " | Balance: " + balance);
    }

    // ---------- Main method ----------
    public static void main(String[] args) {
        // Set bank name and interest rate using static methods
        BankAccount.setBankName("National Bank");
        BankAccount.setInterestRate(0.05);

        // Create accounts (each has its own instance variables)
        BankAccount acc1 = new BankAccount("A101", "Alice", 1000);
        BankAccount acc2 = new BankAccount("A102", "Bob", 2000);

        // Show static members (shared)
        BankAccount.displayBankInfo();

        // Show instance members (unique per object)
        acc1.displayAccountInfo();
        acc2.displayAccountInfo();

        // Instance behavior
        acc1.deposit(500);
        acc2.withdraw(1000);

        // Show interest calculation
        System.out.println("Alice's interest: " + acc1.calculateInterest());
        System.out.println("Bob's interest: " + acc2.calculateInterest());

        // Demonstrate static methods can be called without object
        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts());
    }
}
