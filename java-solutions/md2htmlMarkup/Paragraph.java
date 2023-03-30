package md2htmlMarkup;

import java.util.List;

public class Paragraph extends AbstractMarkup implements Markdown, ContainsInListItem, PrimePart {
    public Paragraph(List<PartOfParagraph> items) {
        super(items, "", "p");
    }
}
