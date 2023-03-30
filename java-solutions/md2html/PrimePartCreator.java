package md2html;

import md2htmlMarkup.*;

import java.util.ArrayList;
import java.util.List;

public class PrimePartCreator {
    private final String block;
    private int currentChar;

    public enum MarkdownToken {
        WORD,
        IMAGE,
        EMPHASIS_STAR,
        STRONG_STAR,
        EMPHASIS_UNDERLINE,
        STRONG_UNDERLINE,
        STRIKEOUT,
        CODE,
        SCREENING,
        NOTHING
    }

    public PrimePartCreator(String block) {
        this.block = block;
        currentChar = 0;
    }

    public PrimePart createPart() {
        PrimePart result;
        if (isHeader()) {
            int levelOfHeader = 0;
            while (levelOfHeader < block.length() && block.charAt(levelOfHeader) == '#') {
                levelOfHeader++;
            }
            currentChar = levelOfHeader + 1;
            result = new Header(buildPart(MarkdownToken.WORD), levelOfHeader);
        } else {
            currentChar = 0;
            result = new Paragraph(buildPart(MarkdownToken.WORD));
        }
        return result;
    }

    private List<PartOfParagraph> buildPart(MarkdownToken currentToken) {
        List<PartOfParagraph> items = new ArrayList<>();
        MarkdownToken token = nextMarkdownToken();
        StringBuilder sb = new StringBuilder();
        while (token != MarkdownToken.NOTHING) {
            if (token == currentToken && currentToken != MarkdownToken.WORD || isSuffix(currentToken)) {
                addToList(items, sb);
                if (!(token == currentToken && currentToken != MarkdownToken.WORD)) {
                    currentChar++;
                }
                break;
            }
            switch (token) {
                case IMAGE -> {
                    addToList(items, sb);
                    StringBuilder name = new StringBuilder();
                    StringBuilder link = new StringBuilder();
                    while (currentChar < block.length() && block.charAt(currentChar) != ']') {
                        name.append(block.charAt(currentChar));
                        currentChar++;
                    }
                    currentChar += 2;
                    while (currentChar < block.length() && block.charAt(currentChar) != ')') {
                        link.append(block.charAt(currentChar));
                        currentChar++;
                    }
                    currentChar++;
                    Image image = new Image(name.toString(), link.toString());
                    items.add(image);
                }
                case STRIKEOUT -> {
                    addToList(items, sb);
                    Strikeout strikeout = new Strikeout(buildPart(token));
                    items.add(strikeout);
                }
                case STRONG_STAR, STRONG_UNDERLINE -> {
                    addToList(items, sb);
                    Strong strong = new Strong(buildPart(token));
                    items.add(strong);
                }
                case EMPHASIS_STAR, EMPHASIS_UNDERLINE -> {
                    addToList(items, sb);
                    Emphasis emphasis = new Emphasis(buildPart(token));
                    items.add(emphasis);
                }
                case SCREENING -> {
                    addToList(items, sb);
                    items.add(new Text(String.valueOf(block.charAt(currentChar))));
                    currentChar++;
                }
                case CODE -> {
                    addToList(items, sb);
                    Code code = new Code(buildPart(token));
                    items.add(code);
                }
                default -> {
                    if (currentChar < block.length()) {
                        if (block.charAt(currentChar) == '<') {
                            sb.append("&lt;");
                        } else if (block.charAt(currentChar) == '>') {
                            sb.append("&gt;");
                        } else if (block.charAt(currentChar) == '&') {
                            sb.append("&amp;");
                        } else {
                            sb.append(block.charAt(currentChar));
                        }
                        currentChar++;
                    }
                }
            }
            token = nextMarkdownToken();
        }
        if (token == MarkdownToken.NOTHING) {
            addToList(items, sb);
        }
        return items;
    }

    private MarkdownToken nextMarkdownToken() {
        if (currentChar == block.length()) {
            return MarkdownToken.NOTHING;
        }
        switch (block.charAt(currentChar)) {
            case '*' -> {
                if (isPrefix("**")) {
                    currentChar += 2;
                    return MarkdownToken.STRONG_STAR;
                } else if (isPrefix("*")) {
                    currentChar += 1;
                    return MarkdownToken.EMPHASIS_STAR;
                }
            }
            case '_' -> {
                if (isPrefix("__")) {
                    currentChar += 2;
                    return MarkdownToken.STRONG_UNDERLINE;
                } else if (isPrefix("_")) {
                    currentChar += 1;
                    return MarkdownToken.EMPHASIS_UNDERLINE;
                }
            }
            case '-' -> {
                if (isPrefix("--")) {
                    currentChar += 2;
                    return MarkdownToken.STRIKEOUT;
                }
            }
            case '`' -> {
                if (isPrefix("`")) {
                    currentChar += 1;
                    return MarkdownToken.CODE;
                }
            }
            case '\\' -> {
                if (isScreening(block.charAt(currentChar + 1))) {
                    currentChar++;
                    return MarkdownToken.SCREENING;
                }
            }
            case '!' -> {
                if (currentChar + 1 < block.length() && block.charAt(currentChar + 1) == '[') {
                    currentChar += 2;
                    return MarkdownToken.IMAGE;
                }
            }
            default -> {
                return MarkdownToken.WORD;
            }
        }
        return MarkdownToken.WORD;
    }

    private static boolean isScreening(char ch) {
        return ch == '*' || ch == '_';
    }

    private boolean isSuffix(MarkdownToken token) {
        if (token == MarkdownToken.WORD) {
            return false;
        }
        if (token == MarkdownToken.IMAGE) {
            return false;
        }
        String suffix = getHighlight(token);
        if (isWordInBlock(suffix, currentChar, currentChar + suffix.length())) {
            currentChar += suffix.length() - 1;
            return true;
        }
        return false;
    }

    private boolean isPrefix(String prefix) {
        int i = currentChar + prefix.length();
        return isValidIndexInBlock(i + prefix.length()) && !isWordInBlock(prefix, i, i + prefix.length()) &&
            isWordInBlock(prefix, i - prefix.length(), i) && !Character.isWhitespace(block.charAt(i));
    }

    private boolean isWordInBlock(String word, int start, int end) {
        return word.equals(block.substring(start, end));
    }

    private boolean isValidIndexInBlock(int index) {
        return index < block.length();
    }

    private boolean isHeader() {
        int levelOfHeader = 0;
        if (block.charAt(levelOfHeader) != '#') {
            return false;
        }
        while (levelOfHeader < block.length() && block.charAt(levelOfHeader) == '#') {
            levelOfHeader++;
        }
        return levelOfHeader < block.length() && Character.isWhitespace(block.charAt(levelOfHeader));
    }

    private static String getHighlight(MarkdownToken token) {
        switch (token) {
            case STRONG_STAR -> {
                return "**";
            }
            case STRONG_UNDERLINE -> {
                return "__";
            }
            case EMPHASIS_STAR -> {
                return "*";
            }
            case EMPHASIS_UNDERLINE -> {
                return "_";
            }
            case CODE -> {
                return "`";
            }
            case STRIKEOUT -> {
                return "--";
            }
            case SCREENING -> {
                return "\\";
            }
            default -> {
                return "";
            }
        }
    }

    private static void addToList(List<PartOfParagraph> items, StringBuilder sb) {
        if (!sb.isEmpty()) {
            items.add(new Text(sb.toString()));
            sb.setLength(0);
        }
    }
}