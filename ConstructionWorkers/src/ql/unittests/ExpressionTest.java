/**
 * ExpressionTest.java.
 */

package ql.unittests;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.binaries.equality.*;
import ql.astnodes.expressions.binaries.logic.AND;
import ql.astnodes.expressions.binaries.logic.OR;
import ql.astnodes.expressions.binaries.numerical.Addition;
import ql.astnodes.expressions.binaries.numerical.Division;
import ql.astnodes.expressions.binaries.numerical.Multiplication;
import ql.astnodes.expressions.binaries.numerical.Subtraction;
import ql.astnodes.expressions.literals.Money;
import ql.astnodes.expressions.literals.MyBoolean;
import ql.astnodes.expressions.literals.MyInteger;
import ql.astnodes.expressions.literals.MyString;
import ql.astnodes.expressions.unaries.Negation;
import ql.astnodes.expressions.unaries.Negative;
import ql.astnodes.expressions.unaries.Positive;
import ql.astnodes.types.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ql.gui.evaluation.QuestionEvaluator;
import ql.gui.evaluation.QuestionValueChecker;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.values.Value;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExpressionTest extends QLTestSetUp {


    private Context context = new Context();
    private QuestionValueChecker questionEvaluator;

    private final MyInteger num4 = new MyInteger(4, null);
    private final MyInteger num2 = new MyInteger(2, null);

    private final Money dec4 = new Money (BigDecimal.valueOf(4.00), null);
    private final Money dec2 = new Money (BigDecimal.valueOf(2.00), null);

    private final MyInteger testInteger = new MyInteger(1, new LineNumber(1));
    private final Money testMoney = new Money (BigDecimal.valueOf(1.00), new LineNumber(1));
    private final MyBoolean testBoolean = new MyBoolean (true, new LineNumber(1));
    private final MyString testString = new MyString ("a", new LineNumber(1));

    @Before
    public void setUp() throws IOException{
        inputFileName = "CorrectForm.ql";
        super.setUp();

        questionEvaluator = new QuestionValueChecker(context);

    }

    @Test
    public void testAllLiterals() {

        Type typeInteger = typeChecker.visit(testInteger);
        Assert.assertEquals(typeInteger.equals(new IntegerType()), true);

        Type typeMoney = typeChecker.visit(testMoney);
        Assert.assertEquals(typeMoney.equals(new MoneyType()), true);

        Type typeBoolean = typeChecker.visit(testBoolean);
        Assert.assertEquals(typeBoolean.equals(new BooleanType()), true);

        Type typeString = typeChecker.visit(testString);
        Assert.assertEquals(typeString.equals(new StringType()), true);

    }

    @Test
    public void testAddition() {
        Addition additionInteger = new Addition(testInteger, testInteger, null);
        Type typeInteger = typeChecker.visit(additionInteger);
        Assert.assertEquals(typeInteger.equals(new IntegerType()), true);

        Addition integerExpression = new Addition(num4, num2,null);
        Value valueInteger = integerExpression.accept(this.questionEvaluator);
        Assert.assertEquals(valueInteger.getValue(), 6);

        Addition additionMoney = new Addition(testMoney, testMoney, null);
        Type typeMoney = typeChecker.visit(additionMoney);
        Assert.assertEquals(typeMoney.equals(new MoneyType()), true);

        Addition moneyExpression = new Addition(dec4, dec2,null);
        Value valueMoney = moneyExpression.accept(this.questionEvaluator);
        Assert.assertEquals(valueMoney.getValue(), BigDecimal.valueOf(6.00).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testSubtraction() {
        Subtraction subtractionInteger = new Subtraction(testInteger, testInteger, null);
        Type typeInteger = typeChecker.visit(subtractionInteger);
        Assert.assertEquals(typeInteger.equals(new IntegerType()), true);

        Subtraction integerExpression = new Subtraction(num4, num2,null);
        Value valueInteger = integerExpression.accept(this.questionEvaluator);
        Assert.assertEquals(valueInteger.getValue(), 2);

        Subtraction subtractionMoney = new Subtraction(testMoney, testMoney, null);
        Type typeMoney = typeChecker.visit(subtractionMoney);
        Assert.assertEquals(typeMoney.equals(new MoneyType()), true);

        Subtraction moneyExpression = new Subtraction(dec4, dec2,null);
        Value valueMoney = moneyExpression.accept(this.questionEvaluator);
        Assert.assertEquals(valueMoney.getValue(), BigDecimal.valueOf(2.00).setScale(2, RoundingMode.HALF_UP));

    }

    @Test
    public void testMultiplication() {
        Multiplication multiInteger = new Multiplication(testInteger, testInteger, null);
        Type typeInteger = typeChecker.visit(multiInteger);
        Assert.assertEquals(typeInteger.equals(new IntegerType()), true);

        Multiplication integerExpression = new Multiplication(num4, num2,null);
        Value valueInteger = integerExpression.accept(this.questionEvaluator);
        Assert.assertEquals(valueInteger.getValue(), 8);

        Multiplication multiMoney = new Multiplication(testMoney, testMoney, null);
        Type typeMoney = typeChecker.visit(multiMoney);
        Assert.assertEquals(typeMoney.equals(new MoneyType()), true);

        Multiplication moneyExpression = new Multiplication(dec4, dec2,null);
        Value valueMoney = moneyExpression.accept(this.questionEvaluator);
        Assert.assertEquals(valueMoney.getValue(), BigDecimal.valueOf(8.00).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testDivision() {
        Division divisionInteger = new Division(testInteger, testInteger, null);
        Type typeInteger = typeChecker.visit(divisionInteger);
        Assert.assertEquals(typeInteger.equals(new IntegerType()), true);

        Division integerExpression = new Division(num4, num2,null);
        Value valueInteger = integerExpression.accept(this.questionEvaluator);
        Assert.assertEquals(valueInteger.getValue(), 2);

        Division divisionMoney = new Division(testMoney, testMoney, null);
        Type typeMoney = typeChecker.visit(divisionMoney);
        Assert.assertEquals(typeMoney.equals(new MoneyType()), true);

        Division moneyExpression = new Division(dec4, dec2,null);
        Value valueMoney = moneyExpression.accept(this.questionEvaluator);
        Assert.assertEquals(valueMoney.getValue(), BigDecimal.valueOf(2.00).setScale(2, RoundingMode.HALF_UP));
    }


    @Test
    public void testLogicExpressions() {
        AND and = new AND(testBoolean, testBoolean, null);
        Type typeAND = typeChecker.visit(and);
        Assert.assertEquals(typeAND.equals(new BooleanType()), true);

        OR or = new OR(testBoolean, testBoolean, null);
        Type typeOR = typeChecker.visit(or);
        Assert.assertEquals(typeOR.equals(new BooleanType()), true);
    }

    @Test
    public void testUnary() {
        Negation negation = new Negation(testBoolean, null);
        Type typeNegation = typeChecker.visit(negation);
        Assert.assertEquals(typeNegation.equals(new BooleanType()), true);

        Negative negative = new Negative(testInteger, null);
        Type typeNegative = typeChecker.visit(negative);
        Assert.assertEquals(typeNegative.equals(new IntegerType()), true);

        Positive positive = new Positive(testInteger, null);
        Type typePositive = typeChecker.visit(positive);
        Assert.assertEquals(typePositive.equals(new IntegerType()), true);

    }

    @Test
    public void testEquality() {
        EQ equal = new EQ(testInteger, testInteger, null);
        Type typeEQ = typeChecker.visit(equal);
        Assert.assertEquals(typeEQ.equals(new BooleanType()), true);

        NEQ notEqual = new NEQ(testInteger, testInteger, null);
        Type typeNEQ = typeChecker.visit(notEqual);
        Assert.assertEquals(typeNEQ.equals(new BooleanType()), true);

        GT gt = new GT(testInteger, testInteger, null);
        Type typeGT = typeChecker.visit(gt);
        Assert.assertEquals(typeGT.equals(new BooleanType()), true);

        LT lt = new LT(testInteger, testInteger, null);
        Type typeLT = typeChecker.visit(lt);
        Assert.assertEquals(typeLT.equals(new BooleanType()), true);

        GTEQ gteq = new GTEQ(testInteger, testInteger, null);
        Type typeGTEQ = typeChecker.visit(gteq);
        Assert.assertEquals(typeGTEQ.equals(new BooleanType()), true);

        LTEQ lteq = new LTEQ(testInteger, testInteger, null);
        Type typeLTEQ = typeChecker.visit(lteq);
        Assert.assertEquals(typeLTEQ.equals(new BooleanType()), true);

    }

}
