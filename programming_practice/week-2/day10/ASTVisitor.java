import java.io.IOException;

public interface ASTVisitor {

    void visit(CreateTableNode node) throws IOException;
    void visit(DropTableNode node) throws IOException;
    void visit(InsertNode node) throws IOException;
    void visit(UpdateNode node) throws IOException;
    void visit(DeleteNode node) throws IOException;

}
