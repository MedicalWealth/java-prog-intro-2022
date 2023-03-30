import java.util.Arrays;

public class IntList{
    private int[] storage = new int[1];
    private int size;

    public void add(int digit) {
        storage[size++] = digit;
        ensureCapacity();
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        return storage[index];
    }

    private void ensureCapacity() {
        if (size == storage.length) {
            storage = Arrays.copyOf(storage, size * 3 / 2 + 1);
        }
    }
}
