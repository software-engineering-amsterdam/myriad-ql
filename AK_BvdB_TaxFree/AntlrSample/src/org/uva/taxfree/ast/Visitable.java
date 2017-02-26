package org.uva.taxfree.ast;

import sun.reflect.generics.visitor.Visitor;

/**
 * Created by Alex on 20-2-2017.
 */
public interface Visitable {
    public void accept(Visitor visitor);
}
