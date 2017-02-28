/**
 * ExpressionTest.java.
 */

package ql.unittests;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Money;
import ql.astnodes.expressions.literals.MyBoolean;
import ql.astnodes.expressions.literals.MyInteger;
import ql.astnodes.expressions.literals.MyString;
import ql.astnodes.types.IntegerType;
import ql.astnodes.types.Type;
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
