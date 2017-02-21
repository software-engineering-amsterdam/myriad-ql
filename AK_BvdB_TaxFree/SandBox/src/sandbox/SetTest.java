package sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;
import java.util.Set;

public class SetTest {
    @Test
    public void testLinkedHashSet() throws Exception{
        Set<String> labelSet = new LinkedHashSet<>();
        Assert.assertTrue(labelSet.isEmpty());
        Assert.assertTrue(labelSet.add("Help"), "Adding a member should change the Set");
        Assert.assertFalse(labelSet.add("Help"), "Adding a member twice should not change the Set");
    }

}
