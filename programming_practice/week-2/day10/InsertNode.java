import java.io.IOException;
import java.util.List;

/**
 * @param columns 선택적: 컬럼명 리스트
 * @param values  값 리스트
 */
public record InsertNode(String tableName, List<String> columns, List<String> values) implements ASTNode {

    @Override
    public void accept(ASTVisitor visitor) throws IOException {
        visitor.visit(this);
    }

}