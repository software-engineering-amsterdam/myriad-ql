package sc.ql.test.Value;

import static org.junit.Assert.*;

import org.junit.Test;

import sc.ql.gui.values.BooleanValue;
import sc.ql.gui.values.IntegerValue;

public class IntergerValueTest {
    @Test
    public void addInteger() {
    	IntegerValue integer1 = new IntegerValue(1);
    	IntegerValue integer2 = new IntegerValue(2);

    	IntegerValue result = integer1.add(integer2);
    	assertEquals(3, (int) result.getValue());
	}
    @Test
    public void substractInteger() {
    	IntegerValue integer1 = new IntegerValue(5);
    	IntegerValue integer2 = new IntegerValue(2);

    	IntegerValue result = integer1.subtract(integer2);
    	assertEquals(3, (int) result.getValue());
	}
    @Test
    public void divideInteger() {
    	IntegerValue integer1 = new IntegerValue(10);
    	IntegerValue integer2 = new IntegerValue(2);

    	IntegerValue result = integer1.divide(integer2);
    	assertEquals(5, (int) result.getValue());
	}
    @Test
    public void multiplyInteger() {
    	IntegerValue integer1 = new IntegerValue(4);
    	IntegerValue integer2 = new IntegerValue(2);

    	IntegerValue result = integer1.multiply(integer2);
    	assertEquals(8, (int) result.getValue());
	}
    @Test
    public void equalInteger() {
    	IntegerValue integer1 = new IntegerValue(2);
    	IntegerValue integer2 = new IntegerValue(2);

    	BooleanValue result = integer1.equals(integer2);
    	assertTrue((boolean) result.getValue());
	}
    @Test
    public void notEqualInteger() {
    	IntegerValue integer1 = new IntegerValue(2);
    	IntegerValue integer2 = new IntegerValue(3);

    	BooleanValue result = integer1.equalsNot(integer2);
    	assertTrue((boolean) result.getValue());
	}
    @Test
    public void greaterThenInteger() {
    	IntegerValue integer1 = new IntegerValue(3);
    	IntegerValue integer2 = new IntegerValue(2);

    	BooleanValue result = integer1.greaterThen(integer2);
    	assertTrue((boolean) result.getValue());
	}
    @Test
    public void greaterThenEqualInteger() {
    	IntegerValue integer1 = new IntegerValue(3);
    	IntegerValue integer2 = new IntegerValue(2);
    	IntegerValue integer3 = new IntegerValue(3);
    	
    	BooleanValue result1 = integer1.greaterThenEqual(integer2);
    	BooleanValue result2 = integer1.greaterThenEqual(integer3);
    	assertTrue((boolean) result1.getValue());
    	assertTrue((boolean) result2.getValue());	
	}
    @Test
    public void lessThenInteger() {
    	IntegerValue integer1 = new IntegerValue(2);
    	IntegerValue integer2 = new IntegerValue(3);
    	
    	BooleanValue result = integer1.lessThen(integer2);
    	assertTrue((boolean) result.getValue());
	}
    @Test
    public void lessThenEqualInteger() {
    	IntegerValue integer1 = new IntegerValue(2);
    	IntegerValue integer2 = new IntegerValue(3);
    	IntegerValue integer3 = new IntegerValue(3);
    	
    	BooleanValue result1 = integer1.lessThenEqual(integer2);
    	BooleanValue result2 = integer1.lessThenEqual(integer3);
    	assertTrue((boolean) result1.getValue());
    	assertTrue((boolean) result2.getValue());	
	}
}
