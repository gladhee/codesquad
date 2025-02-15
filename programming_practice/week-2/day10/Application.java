import java.util.List;

public class Application {

    public static void main(String[] args) {
        MetaFileManager meta = new MetaFileManager("table.meta");
        CSVDatabaseEngine engine = new CSVDatabaseEngine(meta);

        while (true) {
            String input = Input.prompt();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                Lexer lexer = new Lexer(input);
                List<Token> tokens = lexer.tokenize();
                SQLParser parser = new SQLParser(tokens);

                while (parser.peek().type() != TokenType.EOF) {
                    ASTNode stmt = parser.parseStatement();
                    stmt.accept(engine);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Bye!");
    }

}
