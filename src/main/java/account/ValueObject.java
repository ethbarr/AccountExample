package account;

import java.util.function.Predicate;

public abstract class ValueObject<T extends ValueObject<T>> {

    protected abstract Predicate<T> IsEqualTo(T value);
    Predicate<Integer> greaterThanTen = (i) -> i > 10;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        T object = (T)o;
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        return IsEqualTo(object).test(object);
    }
}