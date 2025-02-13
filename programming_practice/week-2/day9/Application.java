public class Application {

    public static void main(String[] args) {
        int input = getInput();
        Hanoi hanoi = new Hanoi(input);
        hanoi.solve();
        System.out.println(hanoi);
    }

    private static int getInput() {
        while (true) {
            try {
                int input = Input.nextInt();
                validateInput(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void validateInput(int input) {
        if (input < 1 || input > 100) {
            throw new IllegalArgumentException("Input must be between 0 and 100");
        }
    }

}
