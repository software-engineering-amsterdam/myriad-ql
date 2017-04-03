package com.matthewchapman.ql.errorhandling;

import com.matthewchapman.ql.environment.values.Value;

/**
 * Created by matt on 21/03/2017.
 */
public class InvalidOperationException extends RuntimeException {

    public final Value value;

    public InvalidOperationException(Value value) {
        this.value = value;
    }

}
