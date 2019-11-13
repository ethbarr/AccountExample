package account;

public class Counter {
    private static int lastValue;

    public Counter(int initialValue) {

        this.lastValue = initialValue;
    }

    public int getNext() {
        return lastValue++;
    }
}
