import java.io.IOException;

public record DeleteNode(String tableName, Expression whereClause) implements ASTNode {

    @Override
    public void accept(ASTVisitor visitor) throws IOException {
        visitor.visit(this);
    }

}