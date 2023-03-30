package md2htmlMarkup;

import java.util.List;

public class UnorderedList extends AbstractMarkup implements ContainsInListItem {
    public UnorderedList(List<ListItem> items) {
        super(items, "", "ul");
    }
}

