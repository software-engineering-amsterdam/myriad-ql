package test.org.uva.taxfree.ql.ast;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.model.environment.Declaration;
import org.uva.taxfree.ql.model.node.declarations.DeclarationNode;
import org.uva.taxfree.ql.model.types.IntegerType;

public class DeclarationTest {
    @Test
    public void testEquals() throws Exception {
        Declaration declaration = new Declaration(new DeclarationNode("This is number one", "numberOne", new IntegerType()));
        Assert.assertTrue(declaration.equals("numberOne"), "equals should compare the string to the variableId");
    }
}