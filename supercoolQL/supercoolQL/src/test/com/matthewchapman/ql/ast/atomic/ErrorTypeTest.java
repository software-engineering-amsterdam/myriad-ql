package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.ast.atomic.type.ErrorType;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.ast.atomic.type.StringType;
import org.junit.Test;

import static org.junit.Assert.assertFalse;


/**
 * Created by matt on 15/03/2017.
 * <p>
 * Tests implementation of ErrorType
 */
public class ErrorTypeTest {

    @Test
    public void checkErrorType() {
        ErrorType error = new ErrorType();
        IntegerType integer = new IntegerType();
        StringType string = new StringType();
        BooleanType bool = new BooleanType();

        assertFalse(error.isCompatible(integer));
        assertFalse(error.isCompatible(string));
        assertFalse(error.isCompatible(bool));
    }
}