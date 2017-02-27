package com.matthewchapman.ql.ast;

import com.matthewchapman.ql.validation.Visitor;

/**
 * Created by matt on 27/02/2017.
 */
public interface Visitable {
    public void accept(Visitor visitor);
}
