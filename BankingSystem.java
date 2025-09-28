abstract class BankAccount {
    double balance;

    BankAccount(double balance) {
        this.balance = balance;
    }

    abstract double calculateInterest();
}

class SavingsAccount extends BankAccount {
    SavingsAccount(double balance) {
        super(balance);
    }

    double calculateInterest() {
        return balance * 0.04;
    }
}

class CurrentAccount extends BankAccount {
    CurrentAccount(double balance) {
        super(balance);
    }

    double calculateInterest() {
        return balance * 0.01;
    }
}

class LoanAccount extends BankAccount {
    LoanAccount(double balance) {
        super(balance);
    }

    double calculateInterest() {
        return balance * 0.08;
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        BankAccount s = new SavingsAccount(10000);
        BankAccount c = new CurrentAccount(20000);
        BankAccount l = new LoanAccount(50000);

        System.out.println("Savings Interest: " + s.calculateInterest());
        System.out.println("Current Interest: " + c.calculateInterest());
        System.out.println("Loan Interest: " + l.calculateInterest());
    }
}
