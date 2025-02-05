import java.util.Arrays;
import java.util.List;

public class DartApplication {

    private final static int ROUND_COUNT = 7;

    private final Input input;
    private final Output output;
    private final Player A;
    private final Player B;

    public DartApplication(Input input, Output output, Player A, Player B) {
        this.input = input;
        this.output = output;
        this.A = A;
        this.B = B;
    }

    public void run() {
        ScoreBoard scoreBoard = createScoreBoard();
        List<Integer> inputNumbers = getInputNumbers();

        DartGame dartGame = new DartGame(scoreBoard);

        playGame(dartGame, inputNumbers);
        output.printResult(A, B);
    }

    private ScoreBoard createScoreBoard() {
        return ScoreBoard.of(List.of(20, 1, 18, 4, 13, 6, 10, 15, 2, 17, 3, 19, 7, 16, 8, 11, 14, 9, 12, 5));
    }

    private List<Integer> getInputNumbers() {
        while (true) {
            try {
                String inputNumbers = input.roundOnlyOneResultB();
//                String inputNumbers = input.roundOnlyThreeResultA();
//                String inputNumbers = input.roundOnlyThreeResultB();

                List<Integer> parsedData = Arrays.stream(inputNumbers.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .peek(this::validateInputNumber)
                        .toList();
                validateInputNumbers(parsedData);

                return parsedData;
            } catch (RuntimeException e) {
                output.printErrorMessage(e.getMessage());
            }
        }
    }

    private void validateInputNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("입력 데이터의 범위가 올바르지 않습니다.");
        }
    }

    private void validateInputNumbers(List<Integer> numbers) {
        if (numbers.size() % ROUND_COUNT != 0) {
            throw new IllegalArgumentException("입력 데이터의 개수가 올바르지 않습니다.");
        }
    }

    private void playGame(DartGame dartGame, List<Integer> inputNumbers) {
        for (int i = 0; i < inputNumbers.size(); i += ROUND_COUNT) {
            List<Integer> roundNumbers = inputNumbers.subList(i, i + ROUND_COUNT);

            dartGame.playRound(roundNumbers, A, B);
        }
    }

}
