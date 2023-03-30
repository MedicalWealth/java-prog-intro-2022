package markup;

import java.util.List;

public class Image implements PartOfParagraph {
    private final String name;
    private final String link;

    public Image(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<img alt='").append(name).append("' src='").append(link).append("'>");
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new UnsupportedOperationException("Images do not support Markdown");
    }
}
