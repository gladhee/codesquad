public class GameApplication {

    private static final int MIN_PLAYER_COUNT = 3;
    private static final int MAX_PLAYER_COUNT = 5;
    private static final char PLAYER_IDENTIFIER = 'A';

    private final Input input;
    private final Output output;
    private final CardFactory cardFactory;

    public GameApplication(Input input, Output output, CardFactory cardFactory) {
        this.input = input;
        this.output = output;
        this.cardFactory = cardFactory;
    }

    public void run() {
        int playerCount = getInputPlayerCount();
        start(playerCount);
    }

    private int getInputPlayerCount() {
        while (true) {
            try {
                int playerCount = Integer.parseInt(input.readLine());
                validatePlayerCount(playerCount);

                return playerCount;
            } catch (IllegalArgumentException e) {
                output.printError("Invalid player count! Please enter a number between 3 and 5. Try again.");
            }
        }
    }

    private void validatePlayerCount(int playerCount) {
        if (playerCount < MIN_PLAYER_COUNT || playerCount > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void start(int playerCount) {
        GameFactory gameFactory = GameFactory.from(cardFactory, playerCount);
        Game game = gameFactory.createGame();
        output.printTurnResult(game.showResult());

        while (game.canProceed()) {
            play(game, playerCount);
        }

        output.printWinner(game.showResult());
    }

    private void play(Game game, int playerCount) {
        for (int i = 0; i < playerCount && game.canProceed(); ++i) {
            String ident = String.valueOf((PLAYER_IDENTIFIER + i));
            if (!game.canPlayerPlay(ident)) {
                continue;
            }
            TurnResult turnResult = game.turn(ident);
            output.printTurnResult(turnResult);
        }
    }

}
