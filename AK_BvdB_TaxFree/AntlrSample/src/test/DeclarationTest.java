package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.model.environment.Declaration;
import org.uva.taxfree.model.node.declarations.DeclarationNode;
import org.uva.taxfree.model.types.IntegerType;

public class DeclarationTest {
    @Test
    public void testEquals() throws Exception {
        Declaration declaration = new Declaration(new DeclarationNode("This is number one", "numberOne", new IntegerType()));
        Assert.assertTrue(declaration.equals("NumberOne"), "equals should compare the string to the variableId");
    }
}