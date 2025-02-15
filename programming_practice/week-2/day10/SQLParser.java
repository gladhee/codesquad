import java.util.ArrayList;
import java.util.List;

public class SQLParser {
    private final List<Token> tokens;
    private int pos = 0;

    public SQLParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token peek() {
        if (pos < tokens.size()) {
            return tokens.get(pos);
        }

        return new Token(TokenType.EOF, "");
    }

    public Token consume() {
        return tokens.get(pos++);
    }

    public Token expect(TokenType type) throws ParseException {
        Token token = peek();
        if (token.type() != type) {
            throw new ParseException("Expected token " + type + " but found " + token.type(), pos);
        }
        return consume();
    }

    public ASTNode parseStatement() throws ParseException {
        Token token = peek();
        return switch(token.type()) {
            case CREATE -> parseCreateTable();
            case DROP -> parseDropTable();
            case INSERT -> parseInsert();
            case UPDATE -> parseUpdate();
            case DELETE -> parseDelete();
            default -> throw new ParseException("Unsupported statement: " + token.lexeme(), pos);
        };
    }

    // CREATE TABLE tableName (colName dataType, ...)
    private ASTNode parseCreateTable() throws ParseException {
        expect(TokenType.CREATE);
        expect(TokenType.TABLE);
        String tableName = expect(TokenType.IDENTIFIER).lexeme();
        expect(TokenType.LEFT_PAREN);
        List<ColumnDefinition> columns = new ArrayList<>();
        while (peek().type() != TokenType.RIGHT_PAREN) {
            String colName = expect(TokenType.IDENTIFIER).lexeme();
            String colType = expect(TokenType.IDENTIFIER).lexeme();
            columns.add(new ColumnDefinition(colName, colType));
            if (peek().type() == TokenType.COMMA) {
                consume();
            } else {
                break;
            }
        }
        expect(TokenType.RIGHT_PAREN);

        if (peek().type() == TokenType.SEMICOLON) {
            consume();
        }

        return new CreateTableNode(tableName, columns);
    }

    // DROP TABLE tableName
    private ASTNode parseDropTable() throws ParseException {
        expect(TokenType.DROP);
        expect(TokenType.TABLE);
        String tableName = expect(TokenType.IDENTIFIER).lexeme();
        if (peek().type() == TokenType.SEMICOLON) {
            consume();
        }

        return new DropTableNode(tableName);
    }

    // INSERT INTO tableName [(col1, col2,...)] VALUES (val1, val2, ...)
    private ASTNode parseInsert() throws ParseException {
        expect(TokenType.INSERT);
        expect(TokenType.INTO);
        String tableName = expect(TokenType.IDENTIFIER).lexeme();
        List<String> columns = new ArrayList<>();
        if (peek().type() == TokenType.LEFT_PAREN) {
            consume();
            while (peek().type() != TokenType.RIGHT_PAREN) {
                columns.add(expect(TokenType.IDENTIFIER).lexeme());
                if (peek().type() == TokenType.COMMA) {
                    consume();
                }
                else {
                    break;
                }
            }
            expect(TokenType.RIGHT_PAREN);
        }
        expect(TokenType.VALUES);
        expect(TokenType.LEFT_PAREN);
        List<String> values = new ArrayList<>();
        while (peek().type() != TokenType.RIGHT_PAREN) {
            Token valToken = peek();
            if (valToken.type() == TokenType.STRING ||
                    valToken.type() == TokenType.NUMBER ||
                    valToken.type() == TokenType.IDENTIFIER) {
                values.add(consume().lexeme());
            } else {
                throw new ParseException("Unexpected token in VALUES: " + valToken.lexeme(), pos);
            }
            if (peek().type() == TokenType.COMMA) {
                consume();
            }
            else {
                break;
            }
        }
        expect(TokenType.RIGHT_PAREN);
        if (peek().type() == TokenType.SEMICOLON) {
            consume();
        }
        return new InsertNode(tableName, columns, values);
    }

    // DELETE FROM tableName WHERE condition
    private ASTNode parseDelete() throws ParseException {
        expect(TokenType.DELETE);
        expect(TokenType.FROM);
        String tableName = expect(TokenType.IDENTIFIER).lexeme();
        Expression whereClause = null;
        if (peek().type() == TokenType.WHERE) {
            consume();
            whereClause = parseExpression();
        }
        if (peek().type() == TokenType.SEMICOLON) consume();
        return new DeleteNode(tableName, whereClause);
    }

    // UPDATE tableName SET col = value, ... WHERE condition
    private ASTNode parseUpdate() throws ParseException {
        expect(TokenType.UPDATE);
        String tableName = expect(TokenType.IDENTIFIER).lexeme();
        expect(TokenType.SET);
        List<Assignment> assignments = new ArrayList<>();
        while (true) {
            String colName = expect(TokenType.IDENTIFIER).lexeme();
            expect(TokenType.EQUALS);
            Token valueToken = consume();
            if (valueToken.type() != TokenType.STRING &&
                    valueToken.type() != TokenType.NUMBER &&
                    valueToken.type() != TokenType.IDENTIFIER) {
                throw new ParseException("Invalid assignment value: " + valueToken.lexeme(), pos);
            }
            assignments.add(new Assignment(colName, valueToken.lexeme()));
            if (peek().type() == TokenType.COMMA) {
                consume();
            } else {
                break;
            }
        }
        Expression whereClause = null;
        if (peek().type() == TokenType.WHERE) {
            consume();
            whereClause = parseExpression();
        }
        if (peek().type() == TokenType.SEMICOLON) consume();
        return new UpdateNode(tableName, assignments, whereClause);
    }

    private Expression parseExpression() throws ParseException {
        Expression left = parseTerm();
        if (peek().type() == TokenType.EQUALS ||
                peek().type() == TokenType.GREATER ||
                peek().type() == TokenType.LESS) {
            Token op = consume();
            Expression right = parseTerm();
            return new BinaryExpression(left, op.lexeme(), right);
        }
        return left;
    }

    private Expression parseTerm() throws ParseException {
        Token token = consume();
        return switch (token.type()) {
            case IDENTIFIER -> new IdentifierExpression(token.lexeme());
            case NUMBER, STRING -> new LiteralExpression(token.lexeme());
            default -> throw new ParseException("Unexpected token in expression: " + token.lexeme(), pos);
        };
    }

}