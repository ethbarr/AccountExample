package account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class CheckingAccountTest {
    CheckingAccount account;

    private Transaction getTransactionByIndex(Collection<Transaction> ts, int index) {
        return ts.stream()
                .filter(transaction -> transaction.id == index)
                .findFirst()
                .get();
    }

    @Before
    public void setUp() throws Exception {
        Collection<Transaction> history = new ArrayList<Transaction>();
        history.add(new Transaction(0,0.00, 0.00, 0.00));
        account = new CheckingAccount(history);
    }

    @Test
    public void givenZeroDeposit_whenDepositIsCalled_balanceIsZero() {

        account.deposit(0.00);
        Assert.assertEquals(0.00, account.getBalance(), 0.001);
    }

    @Test
    public void givenOneDollarDeposit_whenDepositIsCalled_balanceIsOne() {
        account.deposit(1.00);
        Assert.assertEquals(1.00, account.getBalance(), 0.001);
    }

    @Test
    public void givenNegativeDeposit_whendepositIsCalled_balanceUnchanged() {
        account.deposit(-1.00);
        Assert.assertEquals(0.00, account.getBalance(), 0.001);
    }

    @Test
    public void givenNegativeDeposit_whendepositIsCalled_ResultIsFailure() {
        Result result = account.deposit(-1.00);

        Assert.assertEquals(true, result.IsFailure());
        Assert.assertEquals(false, result.IsSuccess);
        Assert.assertEquals("Value is less than zero", result.Error);
    }

    @Test
    public void givenNullDeposit_whendepositIsCalled_ResultIsFailure() {
        Result result = account.deposit(null);

        Assert.assertEquals(true, result.IsFailure());
        Assert.assertEquals(false, result.IsSuccess);
        Assert.assertEquals("Value is null", result.Error);
    }

    @Test
    public void givenNullAmount_whenWithdrawsCalled_ResultIsFailure() {
        Result result = account.withdraw(null);

        Assert.assertEquals(true, result.IsFailure());
        Assert.assertEquals(false, result.IsSuccess);
        Assert.assertEquals("Value is null", result.Error);
    }

    @Test
    public void givenNegativeAmount_whenWithdrawsCalled_ResultIsFailure() {
        Result result = account.withdraw(-1.00);

        Assert.assertEquals(true, result.IsFailure());
        Assert.assertEquals(false, result.IsSuccess);
        Assert.assertEquals("Value is less than zero", result.Error);
    }

    @Test
    public void givenZeroBalance_whenWithdrawsCalled_ResultIsFailure() {
        Result result = account.withdraw(-1.00);

        Assert.assertEquals(true, result.IsFailure());
        Assert.assertEquals(false, result.IsSuccess);
        Assert.assertEquals("Value is less than zero", result.Error);
    }

    @Test
    public void givenMultipleDeposits_WhenGetBalanceIsCalled_BalanceIsCorrect() {
        account.deposit(100.50);
        account.deposit(250.25);
        account.deposit(1000.25);

        Assert.assertEquals(1351.00, account.getBalance(), 0.001);
    }

    @Test
    public void givenMultipleDepositsAndWithdrawals_WhenGetBalanceIsCalled_BalanceIsCorrect() {
        account.deposit(100.50);
        account.deposit(250.25);
        account.deposit(1000.25);
        account.withdraw(100.00);

        Assert.assertEquals(1251.00, account.getBalance(), 0.001);
    }

    @Test
    public void givenListOfTransactions_WhenGetTransactionsIsCalled_HistoryIsCorrect() {
        account.deposit(100.50);
        account.deposit(250.25);
        account.deposit(1000.25);
        account.withdraw(100.00);
        account.withdraw(1000.00);
        Collection<Transaction> ts = account.getTransactions();

        Assert.assertEquals(100.50, getTransactionByIndex(ts, 1).deposit, 0.001);
        Assert.assertEquals(250.25, getTransactionByIndex(ts, 2).deposit, 0.001);
        Assert.assertEquals(1000.25, getTransactionByIndex(ts, 3).deposit, 0.001);
        Assert.assertEquals(100.00, getTransactionByIndex(ts, 4).withdrawal, 0.001);
        Assert.assertEquals( 1000.00, getTransactionByIndex(ts, 5).withdrawal, 0.001);
    }

    @Test
    public void givenListOfTransactions_WhenGetTransactionsIsCalled_AllTransactionValuesAreCorrect() {
        account.deposit(100.50);

        Collection<Transaction> ts = account.getTransactions();

        Assert.assertEquals(100.50, getTransactionByIndex(ts, 1).deposit, 0.001);
        Assert.assertEquals(0.00, getTransactionByIndex(ts, 1).withdrawal, 0.001);
        Assert.assertEquals(100.50, getTransactionByIndex(ts, 1).balance, 0.001);

        account.withdraw(5.00);

        ts = account.getTransactions();

        Assert.assertEquals(0.00, getTransactionByIndex(ts, 2).deposit, 0.001);
        Assert.assertEquals(5.00, getTransactionByIndex(ts, 2).withdrawal, 0.001);
        Assert.assertEquals(95.50, getTransactionByIndex(ts, 2).balance, 0.001);
    }

}