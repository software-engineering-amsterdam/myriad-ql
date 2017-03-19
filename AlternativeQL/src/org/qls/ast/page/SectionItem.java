package org.qls.ast.page;

import org.ql.ast.Node;

public abstract class SectionItem extends Node {
    public abstract <T, C> T accept(SectionItemVisitor<T, C> visitor, C context);
}
