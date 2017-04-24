package org.qls.ast;

import org.ql.ast.identifier.Identifier;
import org.ql.ast.Node;
import org.qls.ast.page.Page;

import java.util.List;

public class StyleSheet extends Node {
    private final Identifier name;
    private final List<Page> pages;

    public StyleSheet(Identifier name, List<Page> pages) {
        this.name = name;
        this.pages = pages;
    }

    public Identifier getName() {
        return name;
    }

    public List<Page> getPages() {
        return pages;
    }

    public Page getPage(int pageIndex) {
        return pages.get(pageIndex);
    }
}
