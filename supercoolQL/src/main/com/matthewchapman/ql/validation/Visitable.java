package com.matthewchapman.ql.validation;

/**
 * Created by matt on 27/02/2017.
 *
 * Provides a generic interface for classes to become "visitable" by the QLVisitor Interface
 */
public interface Visitable {

    <T> T accept(QLVisitor<T> visitor);
}
