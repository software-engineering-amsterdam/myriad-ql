package org.qls.ast;

import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class StyleSheet extends Node {
    private Identifier name;

    public StyleSheet(Identifier name) {
        this.name = name;
    }

    public Identifier getName() {
        return name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }
}
