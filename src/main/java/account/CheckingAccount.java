package account;

import java.util.Collection;

public class CheckingAccount {
    private Collection<Transaction> transactions;

    public CheckingAccount(Collection<Transaction> history){
        transactions = history;
    }

    public Result deposit(Double amount) {
        if (Validator.validate(amount).IsSuccess) {
            transactions.add(
                    Transaction.deposit(amount, getBalance()));
            return Result.Ok();
        }

        return Validator.validate(amount);
    }

    public Result withdraw(Double amount) {
        if (Validator.validate(amount).IsSuccess) {
            transactions.add(
                    Transaction.withdraw(amount, getBalance()));
            return Result.Ok();
        }
        return Validator.validate(amount);
    }

    public double getBalance() {
        return getLastTransaction().balance;
    }

    public Collection<Transaction> getTransactions() {
        Counter counter = new Counter(getLastTransaction().id);
        transactions.forEach(t-> t.id = counter.getNext());
        return transactions;
    }

    private Transaction getLastTransaction() {
        return transactions.stream()
                .reduce((first, second) -> second)
                .get();
    }
}
