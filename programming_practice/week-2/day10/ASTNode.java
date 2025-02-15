import java.io.IOException;

public interface ASTNode {

    void accept(ASTVisitor visitor) throws IOException;

}
