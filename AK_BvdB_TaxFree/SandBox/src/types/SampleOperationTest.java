package types;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleOperationTest {
    @Test
    public void testSomeAddition() throws Exception {
        Num b = new Int(5);

        SampleOperation s = new SampleOperation();
        s.add((Int) b);
        Assert.assertEquals(((Int) b).getValue(), 10, "Java should automatically grab the proper overload");
    }

}