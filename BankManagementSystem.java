import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    // Encapsulated fields
    private int accountNumber;
    private String holderName;
    private double balance;

    // Constructor
    public BankAccount(int accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    // Getters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit method (method overloading for cash & cheque deposit)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void deposit(double amount, String mode) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + " via " + mode);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // Transfer method
    public void transfer(BankAccount toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred " + amount + " to Account " + toAccount.getAccountNumber());
        } else {
            System.out.println("Transfer failed! Check amount/balance.");
        }
    }

    @Override
    public String toString() {
        return "Account No: " + accountNumber + ", Holder: " + holderName + ", Balance: " + balance;
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        // Sample accounts
        accounts.add(new BankAccount(101, "Alice", 5000));
        accounts.add(new BankAccount(102, "Bob", 3000));

        int choice;
        do {
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Display Accounts");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (BankAccount acc : accounts) {
                        System.out.println(acc);
                    }
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    int accNumDep = sc.nextInt();
                    BankAccount accDep = findAccount(accounts, accNumDep);
                    if (accDep != null) {
                        System.out.print("Enter amount: ");
                        double amt = sc.nextDouble();
                        accDep.deposit(amt);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    int accNumW = sc.nextInt();
                    BankAccount accW = findAccount(accounts, accNumW);
                    if (accW != null) {
                        System.out.print("Enter amount: ");
                        double amt = sc.nextDouble();
                        accW.withdraw(amt);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter sender account number: ");
                    int senderNum = sc.nextInt();
                    BankAccount sender = findAccount(accounts, senderNum);

                    System.out.print("Enter receiver account number: ");
                    int receiverNum = sc.nextInt();
                    BankAccount receiver = findAccount(accounts, receiverNum);

                    if (sender != null && receiver != null) {
                        System.out.print("Enter amount: ");
                        double amt = sc.nextDouble();
                        sender.transfer(receiver, amt);
                    } else {
                        System.out.println("Sender/Receiver account not found!");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }

    // Utility function to find account by number
    private static BankAccount findAccount(ArrayList<BankAccount> accounts, int accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accountNumber)
                return acc;
        }
        return null;
    }
}
