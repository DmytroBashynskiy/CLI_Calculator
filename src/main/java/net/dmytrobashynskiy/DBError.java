package net.dmytrobashynskiy;

public enum DBError {
    GREEN, //no errors
    PARENTHESIS, //unpaired parenthesis
    WRONG_EXPRESSION, //weird expression
    EXPONENT_OVERFLOW, //for exponent overflow
    ILLEGAL_LETTERS, //letters in number-only input
    DIV_BY_ZERO // nuff said
}
