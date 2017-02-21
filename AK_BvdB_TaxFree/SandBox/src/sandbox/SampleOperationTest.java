package sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleOperationTest {
    @Test
    public void testSomeAddition() throws Exception {
        Number b = new Integer(8);

        SampleOperation s = new SampleOperation();
        s.add((Integer) b);
        Assert.assertEquals(b.intValue(), 18, "Java should automatically grab the proper overload");
    }

}