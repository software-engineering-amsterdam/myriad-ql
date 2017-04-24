package test.org.uva.taxfree.ql.values;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.model.values.BooleanValue;

public class ValueTests {

    @Test
    public void testTypeComparison() throws Exception {
        Assert.assertTrue(new BooleanValue(true).equals(new BooleanValue(false)), "default compare compares only className");
    }
}
