package mama.aoc;

public class Tuple<K> {

    private K key;
    private int value;

    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public Tuple(K key, int value) {
        this.key = key;
        this.value = value;
    }

    public void increaseValue() {
        value = value + 1;
    }

    public K getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
