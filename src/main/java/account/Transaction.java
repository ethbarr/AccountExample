package account;

public class Transaction {
    public int id;
    public final Double deposit;
    public final Double withdrawal;
    public final Double balance;

    public Transaction(int id, Double deposit, Double withdrawal, Double balance) {
        this.deposit = deposit;
        this.withdrawal = withdrawal;
        this.balance = balance;
    }

    public static Transaction deposit(Double amount, Double balance) {
        return new Transaction(0, amount, 0.00, balance + amount);
    }

    public static Transaction withdraw(Double amount, Double balance) {
        return new Transaction(0,0.00, amount, balance - amount);
    }
}
