package account;

import java.util.function.Predicate;

public class AccountNumber extends ValueObject<AccountNumber> {
    private String _number;

    public String number() {
        return number();
    }

    public AccountNumber(String number) {
        if (number == null)
            throw new IllegalArgumentException("Account number string is null.");

        if (number == "")
            throw new IllegalArgumentException("Account number string is empty.");

        this._number = number;
    }


    @Override
    protected Predicate<AccountNumber> IsEqualTo(AccountNumber accountNumber) {
        return (x -> this._number == x._number);
    }

//    @Override
//    protected Predicate<AccountNumber> IsEqualTo() {
//        return null;
//    }
}
