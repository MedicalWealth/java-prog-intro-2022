package markup;

import java.util.List;

public class Code extends AbstractMarkup implements PartOfParagraph{
    public Code(List<PartOfParagraph> items) {
        super(items, "'", "code");
    }
}
