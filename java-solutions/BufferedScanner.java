import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class BufferedScanner implements Closeable {
    private final Reader in;
    private final char[] cbuf;
    private int curChar, readChars;
    private final int defaultBufferSize = 1024;
    private byte[] rightTypes = new byte[1];
    private char[] specialSymbols = new char[1];

    public enum TypeOfToken {
        TOKEN,
        NEW_LINE,
        NOTHING;
    }

    BufferedScanner(Reader in, byte[] type, char[] symbols) throws IOException, FileNotFoundException {
        this.in = in;
        this.cbuf = new char[this.defaultBufferSize];
        this.rightTypes = Arrays.copyOf(type, type.length);
        this.specialSymbols = Arrays.copyOf(symbols, symbols.length);
    }

    BufferedScanner(Reader in, byte[] type, char[] symbols, int size) throws IOException, FileNotFoundException {
        this.in = in;
        if (size <= 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        this.cbuf = new char[size];
        this.rightTypes = Arrays.copyOf(type, type.length);
        this.specialSymbols = Arrays.copyOf(symbols, symbols.length);
    }

    public String getNext() throws IOException {
        fillBuffer();
        StringBuilder result = new StringBuilder();
        while (readChars != -1 && !isRightChar(cbuf[curChar])) {
            movePointer();
        }
        while (readChars != -1 && isRightChar(cbuf[curChar])) {
            result.append(cbuf[curChar]);
            movePointer();
        }
        return result.toString();
    }

    public String getNextLine() throws IOException {
        fillBuffer();
        StringBuilder result = new StringBuilder();
        while (readChars != -1 && !isNewLine(cbuf[curChar])) {
            result.append(cbuf[curChar]);
            movePointer();
        }
        newLine();
        return result.toString();
    }

    public boolean hasNextLine() throws IOException {
        fillBuffer();
        return (readChars != -1);
    }

    public boolean hasNext() throws IOException {
        fillBuffer();
        while ((readChars != -1) && !isRightChar(cbuf[curChar])) {
            movePointer();
        }
        return (readChars != -1);
    }

    public TypeOfToken whatIsNext() throws IOException {
        fillBuffer();
        while ((readChars != -1) && !isRightChar(cbuf[curChar]) && !isNewLine(cbuf[curChar])) {
            movePointer();
        }
        if (readChars == -1) {
            return TypeOfToken.NOTHING;
        } else if (isNewLine(cbuf[curChar])) {
            newLine();
            return TypeOfToken.NEW_LINE;
        } else {
            return TypeOfToken.TOKEN;
        }
    }

    public void changeTypesSymbols(byte[] type, char[] symbols) {
        rightTypes = Arrays.copyOf(type, type.length);
        specialSymbols = Arrays.copyOf(symbols, symbols.length);
    }

    private void fillBuffer() throws IOException {
        if (curChar == readChars) {
            readChars = in.read(cbuf);
            curChar = 0;
        }
    }

    private boolean isRightChar(char ch) {
        for (byte rightType : rightTypes) {
            if (Character.getType(ch) == rightType) {
                return true;
            }
        }
        for (char specialSymbol : specialSymbols) {
            if (ch == specialSymbol) {
                return true;
            }
        }
        return false;
    }

    private void movePointer() throws IOException {
        curChar++;
        fillBuffer();
    }

    private static boolean isNewLine(char ch) {
        return (ch == '\u2028') || (ch == '\u2029') ||
            (ch == '\u0085') || (ch == '\n') || (ch == '\r');
    }

    private void newLine() throws IOException {
        if (cbuf[curChar] == '\r') {
            movePointer();
            if (cbuf[curChar] == '\n') {
                movePointer();
            }
        } else {
            movePointer();
        }
    }

    public void close() throws IOException {
        in.close();
    }
}