package inheritance;

import inheritance.Count;
import inheritance.QuickCount;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuickCountTest {
    @Test
    public void testInheritance() throws Exception {
        Count c = new QuickCount();
        c.increment();
        Assert.assertEquals(c.getCount(), 2, "Java always calls the overriden method, despite not marking it as abstract");
    }

    @Test
    public void testDerivedCall() throws Exception {
        Count a = new QuickCount();
        Number b = new Integer(5);
        a.increment(b);
        Assert.assertEquals(a.getCount(), 5, "Overriding only works for equal signatures");
    }

}