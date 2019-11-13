package account;

import java.util.Optional;

public class ResultOf<T> extends Result {
    private Optional<T> _value;

    public Optional<T> Value() {
        if (!IsSuccess)
            throw new RuntimeException();

        return _value;
    }

    protected ResultOf(Optional<T> value, boolean isSuccess, String error) {
        super(isSuccess, error);
        _value = value;
    }
}
