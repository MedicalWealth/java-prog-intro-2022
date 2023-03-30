package md2htmlMarkup;

public class Text implements PartOfParagraph {

    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(text);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }
}
