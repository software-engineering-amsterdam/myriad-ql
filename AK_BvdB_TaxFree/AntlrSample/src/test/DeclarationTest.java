package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.model.environment.Declaration;

public class DeclarationTest {
    @Test
    void testEquals() throws Exception {
        Declaration decl = new Declaration("This is number one", "numberOne");
        Assert.assertTrue(decl.equals("NumberOne"), "equals should compare the string to the variableId");
    }
}