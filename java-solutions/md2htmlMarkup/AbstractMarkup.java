package md2htmlMarkup;

import java.util.List;

public abstract class AbstractMarkup implements Markdown, Html {
    protected final List<? extends Markup> items;
    private final String highlightMarkdown;
    private final String highlightHtml;

    protected AbstractMarkup(List<? extends Markup> items, String highlightMarkdown, String highlightHtml) {
        this.items = items;
        this.highlightMarkdown = highlightMarkdown;
        this.highlightHtml = highlightHtml;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(highlightMarkdown);
        for (Markup item: items) {
            item.toMarkdown(sb);
        }
        sb.append(highlightMarkdown);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        if (!highlightHtml.isEmpty()) {
            sb.append("<").append(highlightHtml).append(">");
        }
        for (Markup item: items) {
            item.toHtml(sb);
        }
        if (!highlightHtml.isEmpty()) {
            sb.append("</").append(highlightHtml).append(">");
        }
    }
}
