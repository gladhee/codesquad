public class Allocation {

    private final int ptr;
    private final int size;
    private final String type;

    public Allocation(int ptr, int size, String type) {
        this.ptr = ptr;
        this.size = size;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public String toString(int base) {
        return String.format("Address: 0x%04X, Type: %s, Size: %d", base + ptr, type, size);
    }

}
