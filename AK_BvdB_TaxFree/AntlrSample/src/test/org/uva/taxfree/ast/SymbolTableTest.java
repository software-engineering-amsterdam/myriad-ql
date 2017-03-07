package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.declarations.BooleanQuestion;

public class SymbolTableTest {
    SymbolTable mSymbolTable;

    @BeforeMethod
    public void setUp() {
        mSymbolTable = new SymbolTable();
    }

    @Test
    public void testEvaluate() throws Exception {
        BooleanQuestion boolQuestion = new BooleanQuestion("did you sell a house?", "hasSoldHouse");
        mSymbolTable.addDeclaration(boolQuestion);
        Assert.assertEquals("false", mSymbolTable.resolveValue("hasSoldHouse"));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testUnknownVariable() throws Exception {
        mSymbolTable.resolveValue("giveMeARuntimeException!");
    }
}