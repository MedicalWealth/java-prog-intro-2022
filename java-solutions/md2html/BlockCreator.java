package md2html;

import java.util.ArrayList;
import java.util.List;

public class BlockCreator {
    private final String text;
    private final List<String> blocks;

    public BlockCreator(String text) {
        this.text = text;
        blocks = new ArrayList<>();
    }

    public List<String> divideByBlocks() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (isNewLine(text.charAt(i))) {
                i = newLine(i);
                if (i < text.length() && isNewLine(text.charAt(i))) {
                    i = newLine(i);
                    addToBlock(sb);
                } else if (i < text.length()){
                    if (!sb.isEmpty()) {
                        sb.append(System.lineSeparator());
                    }
                    sb.append(text.charAt(i++));
                }
            } else {
                sb.append(text.charAt(i++));
            }
        }
        addToBlock(sb);
        return blocks;
    }

    private void addToBlock(StringBuilder item) {
        if (!item.isEmpty()) {
            blocks.add(item.toString());
            item.setLength(0);
        }
    }

    private int newLine(int i) {
        if (i < text.length() && text.charAt(i) == '\r') {
            i++;
            if (i < text.length() && text.charAt(i) == '\n') {
                i++;
            }
        } else {
            i++;
        }
        return i;
    }

    private static boolean isNewLine(char ch) {
        return (ch == '\u2028') || (ch == '\u2029') ||
            (ch == '\u0085') || (ch == '\n') || (ch == '\r');
    }
}
