public record Token(TokenType type, String lexeme) {

    @Override
    public String toString() {
        return "Token(" + type + ", \"" + lexeme + "\")";
    }

}
