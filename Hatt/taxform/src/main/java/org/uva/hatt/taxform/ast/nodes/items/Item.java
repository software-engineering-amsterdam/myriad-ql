package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public interface Item {
    <T> T accept(Visitor<T> visitor);
}
