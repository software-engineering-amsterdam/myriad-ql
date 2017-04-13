package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.visitors.Visitor;

public abstract class Item extends ASTNode{
    public abstract <T> T accept(Visitor<T> visitor);

    public Item(int lineNumber) {
        super(lineNumber);
    }
}
