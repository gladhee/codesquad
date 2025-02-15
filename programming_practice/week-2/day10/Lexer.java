import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char COMMA = ',';
    private static final char SEMICOLON = ';';
    private static final char EQUALS = '=';
    private static final char GREATER = '>';
    private static final char LESS = '<';
    private static final char DOUBLE_QUOTE = '"';
    private static final char EOF = '\0';

    private final String input;
    private int pos;
    private final int length;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
        this.length = input.length();
    }

    // 전체 입력을 토큰 리스트로 변환
    public List<Token> tokenize() throws ParseException {
        List<Token> tokens = new ArrayList<>();
        while (pos < length) {
            skipWhitespace();
            char c = currentChar();
            if (c == EOF) break;
            if (c == DOUBLE_QUOTE) {
                tokens.add(readStringLiteral());
                continue;
            }
            if (isSingleCharToken(c)) {
                tokens.add(singleCharToken(c));
                consume();
                continue;
            }
            if (Character.isDigit(c)) {
                tokens.add(readNumber());
                continue;
            }
            if (Character.isLetter(c) || c == '_') {
                tokens.add(readIdentifierOrKeyword());
                continue;
            }

            throw new ParseException("Unexpected character: " + c, pos);
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    // 식별자 및 키워드 읽기
    private Token readIdentifierOrKeyword() {
        StringBuilder sb = new StringBuilder();
        while (Character.isLetterOrDigit(currentChar()) || currentChar() == '_') {
            sb.append(currentChar());
            consume();
        }
        String word = sb.toString();
        return switch (word.toUpperCase()) {
            case "CREATE" -> new Token(TokenType.CREATE, word);
            case "TABLE" -> new Token(TokenType.TABLE, word);
            case "DROP" -> new Token(TokenType.DROP, word);
            case "INSERT" -> new Token(TokenType.INSERT, word);
            case "INTO" -> new Token(TokenType.INTO, word);
            case "VALUES" -> new Token(TokenType.VALUES, word);
            case "UPDATE" -> new Token(TokenType.UPDATE, word);
            case "SET" -> new Token(TokenType.SET, word);
            case "DELETE" -> new Token(TokenType.DELETE, word);
            case "FROM" -> new Token(TokenType.FROM, word);
            case "WHERE" -> new Token(TokenType.WHERE, word);
            default -> new Token(TokenType.IDENTIFIER, word);
        };
    }

    private Token readStringLiteral() throws ParseException {
        expect(DOUBLE_QUOTE); // 시작하는 " 소비
        StringBuilder sb = new StringBuilder();
        while (currentChar() != DOUBLE_QUOTE && currentChar() != EOF) {
            sb.append(currentChar());
            consume();
        }
        if (currentChar() != DOUBLE_QUOTE) {
            throw new ParseException("Unterminated string literal", pos);
        }
        expect(DOUBLE_QUOTE); // 끝나는 " 소비
        return new Token(TokenType.STRING, sb.toString());
    }

    private Token readNumber() {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(currentChar())) {
            sb.append(currentChar());
            consume();
        }
        return new Token(TokenType.NUMBER, sb.toString());
    }

    // 단일 문자 토큰 생성
    private Token singleCharToken(char c) {
        TokenType type = switch (c) {
            case LEFT_PAREN -> TokenType.LEFT_PAREN;
            case RIGHT_PAREN -> TokenType.RIGHT_PAREN;
            case COMMA -> TokenType.COMMA;
            case SEMICOLON -> TokenType.SEMICOLON;
            case EQUALS -> TokenType.EQUALS;
            case GREATER -> TokenType.GREATER;
            case LESS -> TokenType.LESS;
            default -> throw new IllegalArgumentException("Unexpected character: " + c);
        };
        return new Token(type, String.valueOf(c));
    }

    private boolean isSingleCharToken(char c) {
        return c == LEFT_PAREN || c == RIGHT_PAREN || c == COMMA ||
                c == SEMICOLON || c == EQUALS || c == GREATER || c == LESS;
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(currentChar())) {
            consume();
        }
    }

    private void expect(char c) throws ParseException {
        if (currentChar() != c) {
            throw new ParseException("Expected " + c + " but found " + currentChar(), pos);
        }
        consume();
    }

    private char currentChar() {
        if (pos >= length) {
            return EOF;
        }

        return input.charAt(pos);
    }

    private void consume() {
        pos++;
    }

}