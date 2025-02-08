import java.util.*;

public class Player implements Comparable<Player> {

    private final static int INIT_SCORE = 0;

    private final String ident;
    private final Deque<Card> hand;
    private int score;
    private boolean canPlay;

    private Player(String ident, List<Card> cards) {
        this.ident = ident;
        this.hand = new ArrayDeque<>(cards);
        this.score = INIT_SCORE;
        this.canPlay = true;
    }

    public static Player of(String ident, List<Card> cards) {
        Collections.sort(cards);
        return new Player(ident, cards);
    }

    public List<Card> matchCards() {

        List<Card> matchedCards = new ArrayList<>(matchLeftCards());
        if (!matchedCards.isEmpty()) {
            return matchedCards;
        }

        matchedCards = matchRightCards();

        return matchedCards;
    }

    private List<Card> matchLeftCards() {
        List<Card> matchedCards = new ArrayList<>();

        int count = 0;
        Card standard = hand.peekFirst();
        for (Card card : hand) {
            if (card.compareTo(standard) == 0) {
                count++;
            } else {
                break;
            }
        }

        if (count >= 2) {
            for (int i = 0; i < count; i++) {
                matchedCards.add(hand.removeFirst());
            }
            score++;
        }

        return matchedCards;
    }

    private List<Card> matchRightCards() {
        List<Card> matchedCards = new ArrayList<>();

        int count = 0;
        Card standard = hand.peekLast();
        for (Card card : hand) {
            if (card.compareTo(standard) == 0) {
                count++;
            } else {
                break;
            }
        }

        if (count >= 2) {
            for (int i = 0; i < count; i++) {
                matchedCards.add(hand.removeLast());
            }
            score++;
        }

        return matchedCards;
    }

    public List<Card> matchCardsWith(List<Card> floorCards) {
        List<Card> matchedCards = new ArrayList<>(matchLeftCardsWith(floorCards));
        if (!matchedCards.isEmpty()) {
            score++;
            return matchedCards;
        }

        matchedCards = matchRightCardsWith(floorCards);
        if (!matchedCards.isEmpty()) {
            score++;
            return matchedCards;
        }

        canPlay = false;
        return matchedCards;
    }

    private List<Card> matchLeftCardsWith(List<Card> floorCards) {
        if (floorCards.isEmpty()) {
            return new ArrayList<>();
        }
        List<Card> matchedCards = new ArrayList<>();

        Card standard = hand.getFirst();

        Iterator<Card> iter = floorCards.iterator();
        while (iter.hasNext()) {
            Card card = iter.next();
            if (card.compareTo(standard) == 0) {
                matchedCards.add(card);
                iter.remove();
            }
        }

        if (!matchedCards.isEmpty()) {
            matchedCards.add(hand.removeFirst());
        }

        return matchedCards;
    }

    private List<Card> matchRightCardsWith(List<Card> floorCards) {
        if (floorCards.isEmpty()) {
            return new ArrayList<>();
        }
        List<Card> matchedCards = new ArrayList<>();

        Card standard = hand.getLast();

        Iterator<Card> iter = floorCards.iterator();
        while (iter.hasNext()) {
            Card card = iter.next();
            if (card.compareTo(standard) == 0) {
                matchedCards.add(card);
                iter.remove();
            }
        }

        if (!matchedCards.isEmpty()) {
            matchedCards.add(hand.removeLast());
        }

        return matchedCards;
    }

    public String getIdent() {
        int c = Integer.parseInt(ident);

        return String.format("%c", c);
    }

    public boolean hasLost() {
        return hand.isEmpty() || !canPlay;
    }

    public void disablePlay() {
        canPlay = false;
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.score, other.score);
    }

    @Override
    public String toString() {
        int c = Integer.parseInt(ident);

        return String.format("%c : %d %s", c, score, hand);
    }

}
