package md2htmlMarkup;

import java.util.List;

public class Strikeout extends AbstractMarkup implements PartOfParagraph {
    public Strikeout(List<PartOfParagraph> items) {
        super(items, "~", "s");
    }
}
