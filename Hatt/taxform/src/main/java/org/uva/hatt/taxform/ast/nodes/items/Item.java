package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public interface Item {
    public void accept(Visitor visitor);
}
