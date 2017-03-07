package com.matthewchapman.ql.validator;

/**
 * Created by matt on 27/02/2017.
 */
public interface Visitable {

    <T> T accept(QLVisitor<T> visitor);
}
