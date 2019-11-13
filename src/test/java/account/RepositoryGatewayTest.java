package account;

import org.junit.Test;

public class RepositoryGatewayTest {
    @Test
    public void givenNewTransaction_whenCreateIsCalled_newRecordIsAdded() {
        CsvAccountRepository repo = new CsvAccountRepository();
        repo.create(new Transaction(0, 0.00, 0.00, 0.00));
    }

    @Test
    public void givenNewTransaction_whenReadIsCalled_TransactionIsReturned() {
        CsvAccountRepository repo = new CsvAccountRepository();
        Transaction transaction = repo.read(t -> t.id == 0).Value().get();
    }
}
