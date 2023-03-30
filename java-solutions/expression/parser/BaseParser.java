package expression.parser;

public class BaseParser {
    protected static final char END = 0;

    protected final CharSource source;
    protected char ch;

    public BaseParser(CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean take(char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean test(char expected) {
        return ch == expected;
    }

    protected void expect(String chars) {
        for (char ch : chars.toCharArray()) {
            expect(ch);
        }
    }

    protected void expect(char expected) {
        if (!take(expected)) {
            throw source.error("Expected '" + expected + "', found " + errorChar());
        }
    }

    protected boolean between(char min, char max) {
        return min <= ch && ch <= max;
    }

    protected IllegalArgumentException error(String message) {
        return source.error(message);
    }

    protected void checkEof() {
        if (!eof()) {
            throw error("Expected EOF, found " + errorChar());
        }
    }

    protected boolean eof() {
        return ch == END;
    }

    protected String errorChar() {
        return ch == END ? "EOF" : "'" + ch + "'";
    }
}
