public record IdentifierExpression(String name) implements Expression {

    @Override
    public String toString() {
        return name;
    }

}
