import java.io.IOException;
import java.util.List;

public record InsertNode(String tableName, List<String> columns, List<String> values) implements ASTNode {

    @Override
    public void accept(ASTVisitor visitor) throws IOException {
        visitor.visit(this);
    }

}