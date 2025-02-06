import java.util.Scanner;

public class InputView {

    private static Scanner scanner;

    public InputView() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    public String readLine() {
        System.out.println("Usage: \"year, passengerCapacity\" or \"exit\"\nEnter Command:");

        return scanner.nextLine();
    }

}
