package in.ac.skcet.event_manager.attendance;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class PeriodSet {
    private static final int BINARY_SIZE = 7; // 7 bits
    private static final int SIZE = 127; // 7 bits

    private int value;

    public PeriodSet() {
        this(0);
    }

    public PeriodSet(int value) {
        if (value < 0 || value >= (1 << BINARY_SIZE)) {
            throw new IllegalArgumentException("Value must be between 0 and " + ((1 << BINARY_SIZE) - 1));
        }
        this.value = value;
    }

    public void set(int bitIndex) {
        checkIndex(bitIndex);
        value |= (1 << bitIndex);
    }

    public void clear(int bitIndex) {
        checkIndex(bitIndex);
        value &= ~(1 << bitIndex);
    }

    public int setIndex(int bitIndex, boolean status){
        if (status) {
            this.set(bitIndex);
            return this.value;
        }
        this.clear(bitIndex);
        return this.value;
    }

    public boolean get(int bitIndex) {
        checkIndex(bitIndex);
        return (value & (1 << bitIndex)) != 0;
    }

    public void flip(int bitIndex) {
        checkIndex(bitIndex);
        value ^= (1 << bitIndex);
    }

    public int cardinality() {
        int count = 0;
        for (int i = 0; i < BINARY_SIZE; i++) {
            if (get(i)) {
                count++;
            }
        }
        return count;
    }

    public int length() {
        return BINARY_SIZE;
    }

    private void checkIndex(int bitIndex) {
        if (bitIndex < 0 || bitIndex >= BINARY_SIZE) {
            throw new IndexOutOfBoundsException("Bit index out of range: " + bitIndex);
        }
    }

    public void or(PeriodSet other) {
        this.value |= other.value;
    }

    public boolean isEmpty() {
        return value == 0;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = BINARY_SIZE - 1; i >= 0; i--) {
            sb.append(get(i) ? "1" : "0");
        }
        return sb.toString();
    }
}
