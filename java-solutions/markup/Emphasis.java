package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup implements PartOfParagraph{
    public Emphasis(List<PartOfParagraph> items) {
        super(items, "*", "em");
    }
}
