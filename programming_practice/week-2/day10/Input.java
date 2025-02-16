import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static String prompt() {
        System.out.print("SQL> ");
        return scanner.nextLine();
    }

}
