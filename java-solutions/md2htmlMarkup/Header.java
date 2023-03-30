package md2htmlMarkup;
import java.util.List;

public class Header extends AbstractMarkup implements PrimePart {
    public Header(List<PartOfParagraph> items, int level) {
        super(items, "#".repeat(level), "h" + level);
    }
}
