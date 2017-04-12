package sc.ql.test.Value;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import sc.ql.gui.values.BooleanValue;
import sc.ql.gui.values.StringValue;

public class StringValueTest {
    @Test
    public void equalsStringTrue() {
    	StringValue string1 = new StringValue("sentence one");
    	StringValue string2 = new StringValue("sentence one");

    	BooleanValue result = string1.equals(string2);
    	assertTrue(result.toBoolean());
	}
    @Test
    public void equalsStringFalse() {
    	StringValue string1 = new StringValue("sentence one");
    	StringValue string2 = new StringValue("sentence two");

    	BooleanValue result = string1.equals(string2);
    	assertFalse(result.toBoolean());
	}
    @Test
    public void notEqualsStringTrue() {
    	StringValue string1 = new StringValue("sentence one");
    	StringValue string2 = new StringValue("sentence two");

    	BooleanValue result = string1.equalsNot(string2);
    	assertTrue(result.toBoolean());
	}
    @Test
    public void notEqualsStringFalse() {
    	StringValue string1 = new StringValue("sentence one");
    	StringValue string2 = new StringValue("sentence one");

    	BooleanValue result = string1.equalsNot(string2);
    	assertFalse(result.toBoolean());
	}
}
