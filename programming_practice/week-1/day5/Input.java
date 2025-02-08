import java.util.Scanner;

public class Input {

    private static Scanner scanner;

    public Input() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    public String readLine() {
        System.out.println("Please enter a number between 3 and 5.");

        return scanner.nextLine();
    }

    public String goNextTurn() {
        System.out.println("Enter or 'y'...");

        return scanner.nextLine();
    }

}
