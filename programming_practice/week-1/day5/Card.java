public class Card implements Comparable<Card> {

    private final int value;
    private final CardType type;

    private Card(int value, CardType type) {
        this.value = value;
        this.type = type;
    }

    public static Card of(int value, CardType type) {
        return new Card(value, type);
    }

    public boolean isValue(int value) {
        return this.value == value;
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(value, other.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card other = (Card) obj;
        return value == other.value && type == other.type;
    }

    @Override
    public String toString() {
        return String.format("%s%02d", type.getEmoji(), value);
    }

}
