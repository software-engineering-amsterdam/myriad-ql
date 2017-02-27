/**
 * ExpressionTest.java.
 */

package QL.unitTesting;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.literals.Money;
import QL.ASTnodes.expressions.literals.MyBoolean;
import QL.ASTnodes.expressions.literals.MyInteger;
import QL.ASTnodes.expressions.literals.MyString;
import QL.ASTnodes.types.IntegerType;
import QL.ASTnodes.types.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class ExpressionTest extends QLTestSetUp {

    private final MyInteger testInteger = new MyInteger(1, new LineNumber(1));
    private final Money testMoney = new Money (BigDecimal.valueOf(1.00), new LineNumber(1));
    private final MyBoolean testBoolean = new MyBoolean (true, new LineNumber(1));
    private final MyString testString = new MyString ("a", new LineNumber(1));

    @Before
    public void setUp() throws IOException{
        inputFileName = "CorrectForm.ql";
        super.setUp();
    }

    @Test
    public void testInteger() {
        Type type = semanticChecker.getTypeCheck().visit(testInteger);
        Assert.assertEquals(type.getClass(), IntegerType.class);
    }
}
