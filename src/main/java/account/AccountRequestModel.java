package account;

public class AccountRequestModel {
    public int accountNumber;
    public AccountRepository repo;

    public AccountRequestModel(AccountNumber accountNumber, CsvAccountRepository repo) {

    }
}
