import java.io.IOException;

public record DropTableNode(String tableName) implements ASTNode {

    @Override
    public void accept(ASTVisitor visitor) throws IOException {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "DropTableNode(" + tableName + ")";
    }

}
