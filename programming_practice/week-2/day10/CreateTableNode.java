import java.io.IOException;
import java.util.List;

public record CreateTableNode(String tableName, List<ColumnDefinition> columns) implements ASTNode {

    @Override
    public void accept(ASTVisitor visitor) throws IOException {
        visitor.visit(this);
    }

}
