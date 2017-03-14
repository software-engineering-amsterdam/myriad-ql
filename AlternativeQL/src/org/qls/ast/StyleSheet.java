package org.qls.ast;

import org.ql.ast.Identifier;
import org.ql.ast.Node;
import org.qls.ast.page.Page;

import java.util.List;

public class StyleSheet extends Node {
    private Identifier name;
    private List<Page> pages;

    public StyleSheet(Identifier name, List<Page> pages) {
        this.name = name;
        this.pages = pages;
    }

    public Identifier getName() {
        return name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
