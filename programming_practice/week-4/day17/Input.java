import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static String prompt() {
        System.out.print("vfs> ");
        return scanner.nextLine();
    }

    public static void close() {
        scanner.close();
        scanner = null;
    }

}
