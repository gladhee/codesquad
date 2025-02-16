import java.io.IOException;
import java.util.List;

public record UpdateNode(String tableName, List<Assignment> assignments, Expression whereClause) implements ASTNode {

    @Override
    public void accept(ASTVisitor visitor) throws IOException {
        visitor.visit(this);
    }

}
