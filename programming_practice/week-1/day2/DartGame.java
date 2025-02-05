import java.util.ArrayList;
import java.util.List;

public class DartGame {

    private final static int SET_SCORE_COUNT = 3;
    private final static int TURN_COUNT = 3;
    private final static int A_TURN_START_INDEX = 1;
    private final static int A_TURN_END_INDEX = 4;
    private final static int B_TURN_START_INDEX = 4;
    private final static int B_TURN_END_INDEX = 7;


    private final ScoreBoard scoreBoard;

    public DartGame(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void playRound(List<Integer> roundData, Player A, Player B) {
        List<Integer> validateIndexRange = getValidScoreRange(roundData.getFirst());

        List<Integer> ATurnIndexRange = roundData.subList(A_TURN_START_INDEX, A_TURN_END_INDEX);
        List<Integer> BTurnIndexRange = roundData.subList(B_TURN_START_INDEX, B_TURN_END_INDEX);

        playTurn(ATurnIndexRange, A, validateIndexRange);
        playTurn(BTurnIndexRange, B, validateIndexRange);
    }

    private List<Integer> getValidScoreRange(int range) {
        List<Integer> validScoreRange = new ArrayList<>();
        int scoreCount = scoreBoard.getScoreCount();

        for (int i = 0; i < SET_SCORE_COUNT; ++i) {
            int rangeIndex = (range + i) % scoreCount;

            validScoreRange.add(rangeIndex);
        }

        return validScoreRange;
    }

    private void playTurn(List<Integer> turnNumbers, Player player, List<Integer> validateIndexRange) {
        for (int i = 0; i < TURN_COUNT; ++i) {
            int shootingIndex = turnNumbers.get(i);
            if (!validateIndexRange.contains(shootingIndex)) {
                continue;
            }

            int score = scoreBoard.shootDart(shootingIndex);
            player.addScore(score);
        }

        if (checkBonusScore(turnNumbers, validateIndexRange)) {
            player.addScore(scoreBoard.shootDart(turnNumbers.getFirst()));
        }
    }

    private boolean checkBonusScore(List<Integer> turnNumbers, List<Integer> validateIndexRange) {
        return isAllSame(turnNumbers) && validateIndexRange.contains(turnNumbers.getFirst());
    }

    private boolean isAllSame(List<Integer> turnNumbers) {
        return turnNumbers.stream().distinct().count() == 1;
    }

}
