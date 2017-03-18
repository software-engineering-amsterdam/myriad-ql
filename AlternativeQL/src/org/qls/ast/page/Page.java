package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.ql.ast.Node;

import java.util.List;

public class Page extends Node {
    private Identifier identifier;
    private final List<PageItem> pageItems;

    public Page(Identifier identifier, List<PageItem> pageItems) {
        this.identifier = identifier;
        this.pageItems = pageItems;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<PageItem> getPageItems() {
        return pageItems;
    }
}
