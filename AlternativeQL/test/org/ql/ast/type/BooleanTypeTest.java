package org.ql.ast.type;

import org.junit.Test;

import static org.junit.Assert.*;

public class BooleanTypeTest {
    @Test
    public void shouldReturnTrueOnComparableBooleans() {
        BooleanType booleanType = new BooleanType();
        BooleanType booleanToCompare = new BooleanType();

        assertTrue(booleanType.isCompatibleWith(booleanToCompare));
        assertTrue(booleanToCompare.isCompatibleWith(booleanType));
    }

    @Test
    public void shouldReturnFalseOnCompareBooleanWithAnIntegerType() {
        BooleanType booleanType = new BooleanType();
        IntegerType integerType = new IntegerType();

        assertFalse(booleanType.isCompatibleWith(integerType));
        assertFalse(integerType.isCompatibleWith(booleanType));
    }
}