package markup;

import java.util.List;

public class Strong extends AbstractMarkup implements PartOfParagraph {
    public Strong(List<PartOfParagraph> items) {
        super(items, "__", "strong");
    }
}
