package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.declarations.DeclarationNode;
import org.uva.taxfree.model.types.BooleanType;

public class SymbolTableTest {
    SymbolTable mSymbolTable;

    @BeforeMethod
    public void setUp() {
        mSymbolTable = new SymbolTable();
    }

    @Test
    public void testEvaluate() throws Exception {
        DeclarationNode boolQuestion = new DeclarationNode("did you sell a house?", "hasSoldHouse", new BooleanType());
        mSymbolTable.addDeclaration(boolQuestion);
        Assert.assertEquals("false", mSymbolTable.resolveValue("hasSoldHouse"));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testUnknownVariable() throws Exception {
        mSymbolTable.resolveValue("giveMeARuntimeException!");
    }
}