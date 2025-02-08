import java.util.*;
import java.util.function.Consumer;

public class GameFactory {

    private static final Consumer<CardFactory> NO_OP = deck -> {
    };
    private static final char PLAYER_IDENTIFIER = 'A';
    private static final int THREE_PLAYER = 3;
    private static final int FOUR_PLAYER = 4;
    private static final int FIVE_PLAYER = 5;

    private final CardFactory cardFactory;
    private final int playerCount;
    private final Map<Integer, DistributionRule> rules;

    private GameFactory(CardFactory cardFactory, int playerCount) {
        this.cardFactory = cardFactory;
        this.playerCount = playerCount;
        this.rules = setupRules();
    }

    public static GameFactory from(CardFactory cardFactory, int playerCount) {
        return new GameFactory(cardFactory, playerCount);
    }

    private Map<Integer, DistributionRule> setupRules() {
        return Map.of(
                THREE_PLAYER, new DistributionRule(
                        8, 9,
                        CardFactory::adjustCardsForThreePlayers

                ),
                FOUR_PLAYER, new DistributionRule(
                        7, 8,
                        NO_OP // 전처리 없음
                ),
                FIVE_PLAYER, new DistributionRule(
                        6, 6,
                        NO_OP // 전처리 없음
                )
        );
    }

    public Game createGame() {
        DistributionRule rule = rules.get(playerCount);
        rule.deckPreProcessor().accept(cardFactory);

        Map<String, Player> players = makePlayer(rule);
        List<Card> floorCards = cardFactory.dealCards(rule.floorCards());

        if (!cardFactory.isEmpty()) {
            throw new IllegalStateException("Card is not empty");
        }

        return Game.create(players, floorCards);
    }

    private Map<String, Player> makePlayer(DistributionRule rule) {
        Map<String, Player> players = new TreeMap<>();

        for (int i = 0; i < playerCount; i++) {
            List<Card> playerCards = cardFactory.dealCards(rule.cardsPerPlayer());
            String ident = String.valueOf((PLAYER_IDENTIFIER + i));

            players.put(ident, Player.of(ident, playerCards));
        }

        return players;
    }

}