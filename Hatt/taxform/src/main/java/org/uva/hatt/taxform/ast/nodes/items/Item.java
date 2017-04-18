package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.nodes.FormVisitor;

public abstract class Item extends ASTNode {

    public abstract <T> T accept(FormVisitor<T> visitor);

    Item(int lineNumber) {
        super(lineNumber);
    }
}
