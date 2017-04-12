package sc.ql.test.Value;

import static org.junit.Assert.*;

import org.junit.Test;

import sc.ql.gui.values.BooleanValue;

public class BooleanValueTest {
	@Test
    public void andBothTrue() {
    	BooleanValue int1 = new BooleanValue(true);
    	BooleanValue int2 = new BooleanValue(true);

    	BooleanValue result = int1.and(int2);
    	assertTrue(result.toBoolean());
	}
    @Test
    public void andFirstTrue() {
    	BooleanValue int1 = new BooleanValue(true);
    	BooleanValue int2 = new BooleanValue(false);

    	BooleanValue result = int1.and(int2);
    	assertFalse(result.toBoolean());
	}
    @Test
    public void andSeconTrue() {
    	BooleanValue int1 = new BooleanValue(false);
    	BooleanValue int2 = new BooleanValue(true);

    	BooleanValue result = int1.and(int2);
    	assertFalse(result.toBoolean());
	}
    @Test
    public void andBothFalse() {
    	BooleanValue int1 = new BooleanValue(false);
    	BooleanValue int2 = new BooleanValue(false);

    	BooleanValue result = int1.and(int2);
    	assertFalse(result.toBoolean());
	}
	@Test
    public void orBothTrue() {
    	BooleanValue int1 = new BooleanValue(true);
    	BooleanValue int2 = new BooleanValue(true);

    	BooleanValue result = int1.or(int2);
    	assertTrue(result.toBoolean());
	}
    @Test
    public void orFirstTrue() {
    	BooleanValue int1 = new BooleanValue(true);
    	BooleanValue int2 = new BooleanValue(false);

    	BooleanValue result = int1.or(int2);
    	assertTrue(result.toBoolean());
	}
    @Test
    public void orSecondTrue() {
    	BooleanValue int1 = new BooleanValue(false);
    	BooleanValue int2 = new BooleanValue(true);

    	BooleanValue result = int1.or(int2);
    	assertTrue(result.toBoolean());
	}
    @Test
    public void orBothFalse() {
    	BooleanValue int1 = new BooleanValue(false);
    	BooleanValue int2 = new BooleanValue(false);

    	BooleanValue result = int1.or(int2);
    	assertFalse(result.toBoolean());
	}
}
