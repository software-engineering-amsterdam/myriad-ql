package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.ASTNode;

public abstract class Item extends ASTNode {

    public abstract <T> T accept(ItemVisitor<T> visitor);

    Item(int lineNumber) {
        super(lineNumber);
    }
}
