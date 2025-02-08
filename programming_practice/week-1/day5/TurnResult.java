import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record TurnResult(Map<String, Player> players, List<Card> floorCards, List<Card> removedCards,
                         boolean isChanged) {

    private static final String EMPTY = "";

    public static TurnResult from(Map<String, Player> players, List<Card> floorCards, List<Card> removedCards, boolean isChanged) {
        return new TurnResult(players, floorCards, removedCards, isChanged);
    }

    public String putWinner() {
        Player maxPlayer = Collections.max(players.values());
        List<Player> winners = players.values().stream()
                .filter(p -> p.compareTo(maxPlayer) == 0)
                .toList();

        return winners.stream()
                .map(Player::getIdent)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        if (!isChanged) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (Player player : players.values()) {
            sb.append(player).append("\n");
        }
        sb.append("바닥 ").append(floorCards).append("\n")
                .append("제거 ").append(removedCards).append("\n");

        return sb.toString();
    }

}
