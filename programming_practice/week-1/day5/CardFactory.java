import java.util.*;
import java.util.function.Consumer;

public class CardFactory {

    private static final int TWELVE = 12;
    private static final int REMOVE_COUNT = 3;

    private final List<Card> cards;

    public CardFactory() {
        this.cards = initCard();
    }

    private List<Card> initCard() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);

        return cards;
    }

    private List<Card> createCards() {
        List<Card> orderedCard = new ArrayList<>();

        for (int value = 1; value <= 12; value++) {
            for (CardType type : CardType.values()) {
                orderedCard.add(Card.of(value, type));
            }
        }

        return orderedCard;
    }

    public List<Card> dealCards(int count) {
        List<Card> dealCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dealCards.add(cards.removeFirst());
        }
        return dealCards;
    }

    public void adjustCardsForThreePlayers() {
        int toRemove = REMOVE_COUNT;
        Iterator<Card> it = cards.iterator();
        while (it.hasNext() && toRemove > 0) {
            Card card = it.next();
            if (card.isValue(TWELVE)) {
                it.remove();
                toRemove--;
            }
        }
        if (toRemove > 0) {
            throw new IllegalStateException(TWELVE + " not enough");
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

}
