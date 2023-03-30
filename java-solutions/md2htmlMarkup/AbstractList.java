package md2htmlMarkup;

import java.util.List;

public abstract class AbstractList implements ContainsInListItem {
    private final List<ListItem> items;
    private final String highlight;

    protected AbstractList(List<ListItem> items, String highlight) {
        this.items = items;
        this.highlight = highlight;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<").append(highlight).append(">");
        for (ListItem item: items) {
            item.toHtml(sb);
        }
        sb.append("</").append(highlight).append(">");
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("<").append(highlight).append(">");
        for (ListItem item: items) {
            item.toHtml(sb);
        }
        sb.append("</").append(highlight).append(">");
    }
}
