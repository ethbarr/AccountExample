package account;

import java.util.Collection;
import java.util.function.Predicate;

public interface AccountRepository {
    Result create(Transaction transaction);

    ResultOf<Transaction> read(Predicate<Transaction> p);

    Result update(Transaction transaction);

    Result delete(Transaction transaction);

    ResultOf<Collection<Transaction>> readAll();
}
