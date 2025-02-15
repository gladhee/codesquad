public record BinaryExpression(Expression left, String operator, Expression right) implements Expression {

    @Override
    public String toString() {
        return left + " " + operator + " " + right;
    }

}
