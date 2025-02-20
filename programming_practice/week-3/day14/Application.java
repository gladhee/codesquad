import java.util.List;

public class Application {

    public static void main(String[] args) {
        Memory memory = new Memory();

        int base = memory.init(1024);
        memory.setSize("short", 4);
        memory.setSize("int", 8);
        memory.setSize("string", 16);

        int a = memory.malloc("short", 4);
        int b = memory.malloc("int", 4);

        memory.free(a);

        int c = memory.malloc("short", 4);

        List<String> info = memory.heapdump();
        for (String s : info) {
            System.out.println(s);
        }

        byte[] data = "Hello, World!".getBytes();

        memory.writeBytes(a, data);
        String read = memory.readBytes(a + 3);

        System.out.println(read);
    }

}
