package com.matthewchapman.ql.ast;

import com.matthewchapman.ql.validation.visitor.QLVisitor;

/**
 * Created by matt on 27/02/2017.
 * <p>
 * Provides a generic interface for classes to become "visitable" by the QLVisitor Interface
 */
public interface QLVisitable {

    <T> T accept(QLVisitor<T> visitor, String context);
}
