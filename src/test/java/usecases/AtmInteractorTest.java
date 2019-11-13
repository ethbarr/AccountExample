package usecases;

import account.AccountNumber;
import account.AccountRequestModel;
import account.CsvAccountRepository;
import account.GetBalanceInteractor;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AtmInteractorTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void givenNull_whenCtorIsCalled_ExceptionIsThrown() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Account number string is null");

        AccountNumber accountNumber = new AccountNumber(null);
    }

    @Test
    public void givenEmptyString_whenCtorIsCalled_ExceptionIsThrown() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Account number string is empty");

        AccountNumber accountNumber = new AccountNumber("");
    }

    @Test
    public void givenTwoEquivalentAccountNumbers_WhenEqualsInvoked_ReturnsTrue() {
        AccountNumber accountNumber1 = new AccountNumber("00012345");
        AccountNumber accountNumber2 = new AccountNumber("00012345");

        Assert.assertTrue(accountNumber1.equals(accountNumber2));
    }

    @Test
    public void canCreateGetBalanceInteractor() {
        GetBalanceInteractor interactor = new GetBalanceInteractor();
        AccountNumber accountNumber = new AccountNumber("000012345");
        CsvAccountRepository repo = new CsvAccountRepository();
        AccountRequestModel request = new AccountRequestModel(accountNumber, repo);
        interactor.getBalance(request);
    }
}