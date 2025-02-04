import java.util.Arrays;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Convertor convertor = new Convertor();

        System.out.println("Enter a decimal number:");
        while (true) {
            try {
                inputDecimal(sc, convertor);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }

        System.out.println("\nEnter a binary number: ex. [true, false, true]");
        while (true) {
            try {
                inputBinary(sc, convertor);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid binary number.");
            }
        }
    }

    private static void inputDecimal(Scanner sc, Convertor convertor) {
        String inputNum = sc.nextLine();

        int decimal = Integer.parseInt(inputNum);
        System.out.println(Arrays.toString(convertor.dec2bin(decimal)));
    }

    private static void inputBinary(Scanner sc, Convertor convertor) {
        String input = sc.nextLine();
        input = input.substring(1, input.length() - 1);
        String[] splittedInput = input.split(",");
        System.out.println(Arrays.toString(splittedInput));

        boolean[] binary = new boolean[splittedInput.length];
        for (int i = 0; i < splittedInput.length; i++) {
            binary[i] = Boolean.parseBoolean(splittedInput[i].trim());
        }
        System.out.println(convertor.bin2dec(binary));
    }

}
