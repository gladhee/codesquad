import java.util.List;

public class Application {

    public static void main(String[] args) {
        String input = Input.prompt();


        try {
            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();
            SQLParser parser = new SQLParser(tokens);
            CSVDatabaseEngine engine = new CSVDatabaseEngine();

            while (parser.peek().type() != TokenType.EOF) {
                ASTNode stmt = parser.parseStatement();
                stmt.accept(engine);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }\

}
