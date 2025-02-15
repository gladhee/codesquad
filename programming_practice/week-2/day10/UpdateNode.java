import java.io.IOException;
import java.util.List;

/**
 * @param whereClause 여기서는 WHERE절을 간단하게 문자열로 표현 (실제 구현에서는 Expression AST를 사용)
 */
public record UpdateNode(String tableName, List<Assignment> assignments, Expression whereClause) implements ASTNode {

    @Override
    public void accept(ASTVisitor visitor) throws IOException {
        visitor.visit(this);
    }

}
