import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {

    private final Map<String, Player> players;
    private final List<Card> floorCards;
    private final List<Card> removedCards;

    private Game(Map<String, Player> players, List<Card> floorCards) {
        this.players = players;
        this.floorCards = floorCards;
        this.removedCards = new ArrayList<>();
    }

    public static Game create(Map<String, Player> players, List<Card> floorCards) {
        return new Game(players, floorCards);
    }

    public TurnResult showResult() {
        return TurnResult.from(players, floorCards, removedCards, true);
    }

    public TurnResult turn(String ident) {
        Player player = players.get(ident);

        if (tryMatchCards(player.matchCards()) ||
                tryMatchCards(player.matchCardsWith(floorCards))) {
            return TurnResult.from(players, floorCards, removedCards, true);
        }

        player.disablePlay();
        return TurnResult.from(players, floorCards, removedCards, false);
    }

    private boolean tryMatchCards(List<Card> matchedCards) {
        if (matchedCards.isEmpty()) {
            return false;
        }

        removedCards.addAll(matchedCards);
        return true;
    }

    public boolean canPlayerPlay(String ident) {
        Player player = players.get(ident);

        return !player.hasLost();
    }

    public boolean canProceed() {
        return players.values().stream()
                .filter(Player::hasLost)
                .count() < players.size();
    }

}
