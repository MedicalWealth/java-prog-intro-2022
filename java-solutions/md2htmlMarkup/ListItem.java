package md2htmlMarkup;

import java.util.List;

public class ListItem extends AbstractMarkup implements Markup {
    public ListItem(List<ContainsInListItem> items) {
        super(items, "", "li");
    }
}
