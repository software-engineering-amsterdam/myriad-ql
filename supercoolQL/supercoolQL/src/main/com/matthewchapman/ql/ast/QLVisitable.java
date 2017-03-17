package com.matthewchapman.ql.ast;

import com.matthewchapman.ql.validation.visitor.QLVisitor;

/**
 * Created by matt on 27/02/2017.
 * <p>
 * Provides a generic interface for classes to become "visitable" by the QLVisitor Interface
 */
@FunctionalInterface
public interface QLVisitable {

    <T, C> T accept(QLVisitor<T, C> visitor, C context);
}
