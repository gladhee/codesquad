import java.util.List;

public class ScoreBoard {

    private final List<Integer> scores;

    private ScoreBoard(List<Integer> scores) {
        validateScore(scores);
        this.scores = scores;
    }

    public static ScoreBoard of(List<Integer> scores) {
        return new ScoreBoard(scores);
    }

    public int shootDart(int index) {
        if (!validateIndex(index)) {
            return 0;
        }

        return scores.get(index);
    }

    public int getScoreCount() {
        return scores.size();
    }

    private void validateScore(List<Integer> scores) {
        scores.forEach(
                score -> {
                    if (score < 0) {
                        throw new IllegalArgumentException("점수는 0 이상이어야 합니다.");
                    }
                }
        );
    }

    private boolean validateIndex(int index) {
        return index < 0 || index >= scores.size();
    }

}
