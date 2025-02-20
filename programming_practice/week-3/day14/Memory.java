import java.nio.charset.StandardCharsets;
import java.util.*;

public class Memory {

    private static final int MAX_BASE_CAPACITY = 0xA000;
    private static final byte NULL = '\0';
    private static final byte NON_ALLOCATED = -2;
    private static final byte ALLOCATED = -1;
    private static final int PAD_SIZE = 8;

    private static byte[] memory = {};
    private int base;

    private final Map<String, Integer> sizes;
    private final Map<Integer, Allocation> allocations;

    public Memory() {
        sizes = new HashMap<>();
        allocations = new HashMap<>();
    }

    public int init(int capacity) {
        if (memory.length > 0) {
            throw new IllegalArgumentException("Already initialized");
        }

        memory = new byte[capacity];
        Arrays.fill(memory, NON_ALLOCATED);

        base = (int) (Math.random() * MAX_BASE_CAPACITY);
        return base;
    }

    public void setSize(String type, int size) {
        if (sizes.containsKey(type)) {
            throw new IllegalArgumentException("Type already exists");
        }

        if (size == 1 || size == 2 || size == 4 || size == 8 || size == 16 || size == 32) {
            sizes.put(type, size);
        } else {
            throw new IllegalArgumentException("Invalid size");
        }
    }

    public int malloc(String type, int count) {
        if (!sizes.containsKey(type)) {
            throw new IllegalArgumentException("Type not found");
        }

        int typeSize = sizes.get(type);
        int totalSize = typeSize * count;
        int padding = totalSize < PAD_SIZE ? PAD_SIZE - totalSize : 0;
        totalSize += padding;

        int offset = searchFirstFit(totalSize);
        for (int i = offset; i < offset + totalSize; i++) {
            memory[i] = ALLOCATED;
        }

        allocations.put(offset, new Allocation(offset, totalSize, type));

        return offset;
    }

    private int searchFirstFit(int size) {
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == NON_ALLOCATED) {
                int j = i;
                while (j < memory.length && memory[j] == NON_ALLOCATED) {
                    j++;
                }

                if (j - i >= size) {
                    return i;
                }
            }
        }

        throw new NotEnoughMemoryException();
    }

    public int free(int ptr) {
        Allocation allocation = allocations.get(ptr);
        if (allocation == null) {
            throw new IllegalArgumentException("Invalid pointer");
        }

        for (int i = ptr; i < ptr + allocation.getSize(); i++) {
            memory[i] = NON_ALLOCATED;
        }

        int size = allocation.getSize();

        allocations.remove(ptr);
        return size;
    }

    public List<Integer> usage() {
        int used = 0;
        for (int size : sizes.values()) {
            used += size;
        }

        return Arrays.asList(memory.length, used, memory.length - used);
    }

    public List<String> heapdump() {
        List<String> result = new ArrayList<>();

        for (Allocation allocation : allocations.values()) {
            result.add(allocation.toString(base));
        }

        return result;
    }

    public void reset() {
        memory = new byte[0];
        allocations.clear();
        base = 0;
    }

}
