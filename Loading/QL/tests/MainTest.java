import QL.value.BoolValue;
import QL.value.IntegerValue;
import QL.value.StringValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    StringValue strABC = new StringValue("ABC");
    StringValue strABC2 = new StringValue("ABC");
    StringValue strBCD = new StringValue("BCD");

       @Test
    public void constructor() {

        assertSame(strABC.getValue(), strABC.getValue());
        assertSame(strABC.getValue(), strABC2.getValue());
        assertNotSame(strABC.getValue(), strBCD.getValue());

        assertNull(strABC.getValue());
        assertNull(strABC.getValue());
    }

    @Test
    public void operators() {

        assertTrue(strABC.eq(strABC2).getValue());
        assertFalse(strABC.eq(strBCD).getValue());

        assertFalse(strABC.notEq(strABC2).getValue());
        assertTrue(strABC.notEq(strBCD).getValue());
        // TODO add others

    }

    IntegerValue int1 = new IntegerValue(1);
    IntegerValue int1Copy = new IntegerValue(1);
    IntegerValue int2 = new IntegerValue(2 );

    @Test
    public void constructorInt() {

        assertSame(int1.getValue(), int1.getValue());
        assertSame(int1Copy.getValue(), int1Copy.getValue());
        assertNotSame(int1.getValue(), int2.getValue());

        assertNull(int1.getValue());
        assertNull(int1.getValue());
    }

    @Test
    public void operatorsInt() {

        assertTrue(int1.eq(int1Copy).getValue());
        assertFalse(int1.eq(int2).getValue());

        assertFalse(int1.notEq(int1Copy).getValue());
        assertTrue(int1.notEq(int2).getValue());
        // TODO assertNULL for other operators?

    }

    BoolValue bool1 = new BoolValue(Boolean.TRUE);
    BoolValue bool1Copy = new BoolValue(Boolean.TRUE);
    BoolValue bool2 = new BoolValue(Boolean.FALSE);

    @Test
    public void constructorBool() {
        assertSame(bool1.getValue(), bool1.getValue());
        assertSame(bool1.getValue(), bool1Copy.getValue());
        assertNotSame(bool1.getValue(), bool2.getValue());

        assertNull(bool1.getValue());
        assertNull(bool1.getValue());
    }
}