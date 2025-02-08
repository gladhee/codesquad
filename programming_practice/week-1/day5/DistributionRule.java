import java.util.function.Consumer;

public record DistributionRule(
        int cardsPerPlayer,
        int floorCards,
        Consumer<CardFactory> deckPreProcessor) {}