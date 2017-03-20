package test.org.uva.taxfree.ql;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.declarations.DeclarationNode;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.IntegerType;

import java.io.File;

public class SymbolTableTest extends SemanticsTester {
    private final SourceInfo mEmptySourceInfo = new SourceInfo(0, 0, 0, 0);
    private SymbolTable mSymbolTable;

    @BeforeMethod
    public void setUp() {
        mSymbolTable = new SymbolTable();
    }

    @Test
    public void testEvaluate() throws Exception {
        DeclarationNode boolQuestion = new DeclarationNode("did you sell a house?", "hasSoldHouse", new BooleanType(), mEmptySourceInfo);
        mSymbolTable.addDeclaration(boolQuestion);
        Assert.assertEquals("false", mSymbolTable.resolveValue("hasSoldHouse"));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testUnknownVariable() throws Exception {
        mSymbolTable.resolveValue("giveMeARuntimeException!");
    }

    @Test
    public void testSymbolContents() throws Exception {
        assertSemantics("SimpleForm.txfrm", 0, "Simple tax form without errors");
    }

    @Override
    protected File testFile(String fileName) {
        return new File("forms\\" + fileName);
    }

    @Test
    public void testWritingAndReading() throws Exception {
        String variableId = "intDecl";
        Assert.assertFalse(mSymbolTable.contains(variableId), "empty table should not contain anything");
        mSymbolTable.addDeclaration(new DeclarationNode("newDeclaration", variableId, new IntegerType(), mEmptySourceInfo));
        Assert.assertTrue(mSymbolTable.contains(variableId), "newly added declaration should be present");
        Assert.assertEquals(mSymbolTable.resolveValue(variableId), "0", "Expect default value");
        mSymbolTable.updateValue(variableId, "30");
        Assert.assertEquals(mSymbolTable.resolveValue(variableId), "30", "Expect new value");
    }

}