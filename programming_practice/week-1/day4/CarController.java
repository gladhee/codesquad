import java.util.List;

public class CarController {

    private static final int YEAR_INDEX = 0;
    private static final int CAPACITY_INDEX = 1;
    private static final String EXIT = "exit";
    private static final int MIN_YEARMONTH = 100000;
    private static final int MAX_YEARMONTH = 999999;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MIN_CAPACITY = 0;
    private static final int VALID_INPUT_SIZE = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final CarService carService;

    public CarController(InputView inputView, OutputView outputView, CarService carService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.carService = carService;
    }

    public void run() {
        while (true) {
            List<Integer> yearMonthAndCapacity = getInputLine();
            String result = solution(yearMonthAndCapacity);
            outputView.printResult(result);
        }
    }

    private String solution(List<Integer> yearMonthAndCapacity) {
        int yearMonth = yearMonthAndCapacity.get(YEAR_INDEX);
        int passengerCapacity = yearMonthAndCapacity.get(CAPACITY_INDEX);

        return carService.searchByYearMonthAndCapacity(yearMonth, passengerCapacity);
    }

    private List<Integer> getInputLine() {
        while (true) {
            try {
                String inputLine = inputView.readLine();
                if (inputLine.equals(EXIT)) {
                    outputView.printExit();
                    System.exit(0);
                }
                List<Integer> yearMonthAndCapacity = Parser.parseYearMonthAndCapacity(inputLine);
                validateYearAndCapacity(yearMonthAndCapacity);

                return yearMonthAndCapacity;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void validateYearAndCapacity(List<Integer> yearMonthAndCapacity) {
        if (yearMonthAndCapacity.size() != VALID_INPUT_SIZE) {
            throw new IllegalArgumentException("Invalid input. Please enter two numbers separated by a comma.");
        }

        int yearMonth = yearMonthAndCapacity.get(YEAR_INDEX);
        int passengerCapacity = yearMonthAndCapacity.get(CAPACITY_INDEX);

        validateYearMonth(yearMonth);
        validateCapacity(passengerCapacity);
    }

    private void validateYearMonth(int yearMonth) {
        if (yearMonth < MIN_YEARMONTH || yearMonth > MAX_YEARMONTH) {
            throw new IllegalArgumentException("Invalid year. Please enter a 6-digit number.");
        }

        int month = yearMonth % 100;
        if (month < MIN_MONTH || month > MAX_MONTH) {
            throw new IllegalArgumentException("Invalid month. Please enter a number between 1 and 12.");
        }
    }

    private void validateCapacity(int capacity) {
        if (capacity < MIN_CAPACITY) {
            throw new IllegalArgumentException("Invalid capacity. Please enter a positive number.");
        }
    }

}
