package sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class QuickCountTest {
    @Test
    public void testInheritance() throws Exception {
        Count c = new QuickCount();
        c.increment();
        Assert.assertEquals(c.getCount(), 2, "Java always calls the overriden method, despite not marking it as abstract");
    }

}