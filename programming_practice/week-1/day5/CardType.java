public enum CardType {

    Dog("dog", "🐶"),
    Cat("cat", "🐱"),
    Cow("cow", "🐮");

    private final String ident;
    private final String emoji;

    CardType(String ident, String emoji) {
        this.ident = ident;
        this.emoji = emoji;
    }

    public String getIdent() {
        return ident;
    }

    public String getEmoji() {
        return emoji;
    }

    public static CardType fromName(String ident) {
        for (CardType type : values()) {
            if (type.getIdent().equals(ident)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown cart type: " + ident);
    }

}
