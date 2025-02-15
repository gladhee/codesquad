public enum TokenType {

    // Keyword
    CREATE,
    TABLE,
    INSERT,
    INTO,
    VALUES,
    UPDATE,
    SET,
    DELETE,
    FROM,
    WHERE,
    DROP,

    // Symbol
    LEFT_PAREN,
    RIGHT_PAREN,
    COMMA,
    SEMICOLON,
    EQUALS,
    GREATER,
    LESS,
    DOUBLE_QUOTE,

    // literal
    IDENTIFIER,
    STRING,
    NUMBER,

    // End of Input
    EOF

}
