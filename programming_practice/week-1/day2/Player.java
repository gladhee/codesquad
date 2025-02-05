public class Player implements Comparable<Player> {

    private final static int INITIAL_SCORE = 0;
    private int score;

    public Player() {
        this.score = INITIAL_SCORE;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Player player)) {
            return false;
        }

        return player.score == this.score;
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.score, other.score);
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }

}
