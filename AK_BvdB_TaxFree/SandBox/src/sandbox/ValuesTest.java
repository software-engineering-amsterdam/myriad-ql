package sandbox;

import jdk.internal.org.objectweb.asm.tree.analysis.BasicVerifier;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValuesTest {
    @Test
    public void testDynamicCreation() throws Exception {
        BaseValue a = new BooleanValue();
        a.printClassName();
        BaseValue b = createValue("boolean");

        Assert.assertNotNull(b, "We should be able to create a boolean");
        Assert.assertEquals(b.getType(), "booelan", "should be subclassed type!");
    }

    @Test
    public void testCasting() throws Exception {
        // How to cast this stuff to a BaseValue => DynamicCast
        Class<?> c = Class.forName("sandbox.BooleanValue");

        Object a = new BooleanValue();
        BaseValue b = (BaseValue)a;

        Assert.assertNotNull(c, "Creating class");
       // BaseValue a = new BaseValue();
        //Assert.assertEquals(a.getType(), "boolean", "casting should yield derived behaviour");
    }

    @Test
    public void testCapitalization() throws Exception {
        Assert.assertEquals(capitalize("hallo"), "Hallo", "Should capitalize first character");
        Assert.assertEquals(capitalize("a"), "A", "Should work with single character");
        Assert.assertEquals(capitalize(""), "", "Empty string returns empty string");
        Assert.assertEquals(capitalize("HELLO"), "HELLO", "Capitalized remains unchanged");
    }

    private BaseValue createValue(String type) {
        String questionType = capitalize(type);
        try {
            Class<?> base = Class.forName("sandbox." + questionType + "Value");
            return new BaseValue(); // Fix this
        } catch (ClassNotFoundException e) {
            ;
        }
        return null;
    }

    private String capitalize(String input) {
        if (0 == input.length()) {
            return "";
        }
        String first = input.substring(0, 1).toUpperCase();
        return first + input.substring(1);
    }
}