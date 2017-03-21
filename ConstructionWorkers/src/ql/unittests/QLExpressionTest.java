/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/unittests/QLExpressionTest.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.unittests;

import ql.astnodes.Form;
import ql.astnodes.LineNumber;
import ql.astnodes.expressions.binaries.equality.*;
import ql.astnodes.expressions.binaries.logic.AND;
import ql.astnodes.expressions.binaries.logic.OR;
import ql.astnodes.expressions.binaries.numerical.Addition;
import ql.astnodes.expressions.binaries.numerical.Division;
import ql.astnodes.expressions.binaries.numerical.Multiplication;
import ql.astnodes.expressions.binaries.numerical.Subtraction;
import ql.astnodes.expressions.literals.*;
import ql.astnodes.expressions.unaries.Negation;
import ql.astnodes.expressions.unaries.Negative;
import ql.astnodes.expressions.unaries.Positive;
import ql.astnodes.types.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ql.gui.evaluation.ExpressionEvaluator;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.values.Value;
import ql.semanticchecker.TypeChecker;
import ql.semanticchecker.messagehandling.MessageData;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QLExpressionTest {

    private Context context = new Context();
    private ExpressionEvaluator expressionEvaluator;
    private TypeChecker typeChecker;

    private final MyInteger testInteger = new MyInteger(1, new LineNumber(1));
    private final MyInteger testInteger2 = new MyInteger(2, null);
    private final MyInteger testInteger4 = new MyInteger(4, null);

    private final Money testMoney = new Money (BigDecimal.valueOf(1.00), new LineNumber(1));
    private final Money testMoney2 = new Money (BigDecimal.valueOf(2.00), null);
    private final Money testMoney4 = new Money (BigDecimal.valueOf(4.00), null);

    private final MyBoolean testBoolean = new MyBoolean (true, new LineNumber(1));

    private final MyString testString = new MyString ("a", new LineNumber(1));

    @Before
    public void setUp() throws IOException{
        Form form = new Form(new Identifier("testID", null), new ArrayList<>(), null);

        MessageData messages = new MessageData();
        Map<String, Type> identifierToTypeMap = new HashMap<>();

        typeChecker= new TypeChecker(form, identifierToTypeMap, messages);
        expressionEvaluator = new ExpressionEvaluator(context);
    }

    @Test
    public void testTypeCheckerIntegerType() {
        Type integerType = typeChecker.visit(testInteger);
        Assert.assertEquals(integerType.equals(new IntegerType()), true);
    }

    @Test
    public void testTypeCheckerMoneyType() {
        Type moneyType = typeChecker.visit(testMoney);
        Assert.assertEquals(moneyType.equals(new MoneyType()), true);
    }

    @Test
    public void testTypeCheckerBooleanType() {
        Type booleanType = typeChecker.visit(testBoolean);
        Assert.assertEquals(booleanType.equals(new BooleanType()), true);
    }

    @Test
    public void testTypeCheckerStringType() {
        Type stringType = typeChecker.visit(testString);
        Assert.assertEquals(stringType.equals(new StringType()), true);
    }

    @Test
    public void testTypeCheckerIntegerAddition() {
        Addition integerAddition = new Addition(testInteger, testInteger, null);
        Type integerAdditionType = typeChecker.visit(integerAddition);
        Assert.assertEquals(integerAdditionType.equals(new IntegerType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerAddition() {
        Addition integerAddition = new Addition(testInteger2, testInteger4, null);
        Value integerAdditionValue = integerAddition.accept(expressionEvaluator);
        Assert.assertEquals(integerAdditionValue.getValue(), 6);
    }

    @Test
    public void testTypeCheckerMoneyAddition() {
        Addition moneyAddition = new Addition(testMoney, testMoney, null);
        Type moneyAdditionType = typeChecker.visit(moneyAddition);
        Assert.assertEquals(moneyAdditionType.equals(new MoneyType()), true);
    }

    @Test
    public void testExpressionEvaluatorMoneyAddition() {
        Addition moneyAddition = new Addition(testMoney2, testMoney4,null);
        Value moneyAdditionValue = moneyAddition.accept(expressionEvaluator);
        Assert.assertEquals(moneyAdditionValue.getValue(), BigDecimal.valueOf(6.00).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testTypeCheckerIntegerSubtraction() {
        Subtraction integerSubtraction = new Subtraction(testInteger, testInteger, null);
        Type integerSubtractionType = typeChecker.visit(integerSubtraction);
        Assert.assertEquals(integerSubtractionType.equals(new IntegerType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerSubtraction() {
        Subtraction integerSubtraction = new Subtraction(testInteger4, testInteger2, null);
        Value integerSubtractionValue = integerSubtraction.accept(expressionEvaluator);
        Assert.assertEquals(integerSubtractionValue.getValue(), 2);
    }

    @Test
    public void testTypeCheckerMoneySubtraction() {
        Subtraction moneySubtraction = new Subtraction(testMoney, testMoney, null);
        Type moneySubtractionType = typeChecker.visit(moneySubtraction);
        Assert.assertEquals(moneySubtractionType.equals(new MoneyType()), true);
    }

    @Test
    public void testExpressionEvaluatorMoneySubtraction() {
        Subtraction moneySubtraction = new Subtraction(testMoney4, testMoney2,null);
        Value moneySubtractionValue = moneySubtraction.accept(expressionEvaluator);
        Assert.assertEquals(moneySubtractionValue.getValue(), BigDecimal.valueOf(2.00).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testTypeCheckerIntegerMultiplication() {
        Multiplication integerMultiplication = new Multiplication(testInteger, testInteger, null);
        Type integerMultiplicationType = typeChecker.visit(integerMultiplication);
        Assert.assertEquals(integerMultiplicationType.equals(new IntegerType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerMultiplication() {
        Multiplication integerMultiplication = new Multiplication(testInteger2, testInteger4, null);
        Value integerMultiplicationValue = integerMultiplication.accept(expressionEvaluator);
        Assert.assertEquals(integerMultiplicationValue.getValue(), 8);
    }

    @Test
    public void testTypeCheckerMoneyMultiplication() {
        Multiplication moneyMultiplication = new Multiplication(testMoney, testMoney, null);
        Type moneyMultiplicationType = typeChecker.visit(moneyMultiplication);
        Assert.assertEquals(moneyMultiplicationType.equals(new MoneyType()), true);
    }

    @Test
    public void testExpressionEvaluatorMoneyMultiplication() {
        Multiplication moneyMultiplication = new Multiplication(testMoney2, testMoney4,null);
        Value moneyMultiplicationValue = moneyMultiplication.accept(expressionEvaluator);
        Assert.assertEquals(moneyMultiplicationValue.getValue(), BigDecimal.valueOf(8.00).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testTypeCheckerIntegerDivision() {
        Division integerDivision = new Division(testInteger, testInteger, null);
        Type integerDivisionType = typeChecker.visit(integerDivision);
        Assert.assertEquals(integerDivisionType.equals(new IntegerType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerDivision() {
        Division integerDivision = new Division(testInteger4, testInteger2, null);
        Value integerDivisionValue = integerDivision.accept(expressionEvaluator);
        Assert.assertEquals(integerDivisionValue.getValue(), 2);
    }

    @Test
    public void testTypeCheckerMoneyDivision() {
        Division moneyDivision = new Division(testMoney, testMoney, null);
        Type moneyDivisionType = typeChecker.visit(moneyDivision);
        Assert.assertEquals(moneyDivisionType.equals(new MoneyType()), true);
    }

    @Test
    public void testExpressionEvaluatorMoneyDivision() {
        Division moneyDivision = new Division(testMoney4, testMoney2,null);
        Value moneyDivisionValue = moneyDivision.accept(expressionEvaluator);
        Assert.assertEquals(moneyDivisionValue.getValue(), BigDecimal.valueOf(2.00).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testTypeCheckerANDExpression() {
        AND andExpression = new AND(testBoolean, testBoolean, null);
        Type andExpressionType = typeChecker.visit(andExpression);
        Assert.assertEquals(andExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorANDExpression() {
        AND andExpression = new AND(testBoolean, testBoolean, null);
        Value andExpressionValue = andExpression.accept(expressionEvaluator);
        Assert.assertEquals(andExpressionValue.getValue(), true);
    }

    @Test
    public void testTypeCheckerORExpression() {
        OR orExpression = new OR(testBoolean, testBoolean, null);
        Type orExpressionType = typeChecker.visit(orExpression);
        Assert.assertEquals(orExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorORExpression() {
        OR orExpression = new OR(testBoolean, new MyBoolean(false, null), null);
        Value orExpressionValue = orExpression.accept(expressionEvaluator);
        Assert.assertEquals(orExpressionValue.getValue(), true);
    }

    @Test
    public void testTypeCheckerNegationExpression() {
        Negation negationExpression = new Negation(testBoolean, null);
        Type negationExpressionType = typeChecker.visit(negationExpression);
        Assert.assertEquals(negationExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorNegationExpression() {
        Negation negationExpression = new Negation(testBoolean, null);
        Value negationExpressionValue = negationExpression.accept(expressionEvaluator);
        Assert.assertEquals(negationExpressionValue.getValue(), false);
    }

    @Test
    public void testExpressionEvaluatorNegativeIntegerExpression() {
        Negative negativeIntegerExpression = new Negative(testInteger, null);
        Value negativeIntegerExpressionValue = negativeIntegerExpression.accept(expressionEvaluator);
        Assert.assertEquals(negativeIntegerExpressionValue.getValue(), -1);
    }

    @Test
    public void testTypeCheckerNegativeMoneyExpression() {
        Negative negativeMoneyExpression = new Negative(testMoney, null);
        Type negativeMoneyExpressionType = typeChecker.visit(negativeMoneyExpression);
        Assert.assertEquals(negativeMoneyExpressionType.equals(new MoneyType()), true);
    }

    @Test
    public void testTypeCheckerNegativeIntegerExpression() {
        Negative negativeIntegerExpression = new Negative(testInteger, null);
        Type negativeIntegerExpressionType = typeChecker.visit(negativeIntegerExpression);
        Assert.assertEquals(negativeIntegerExpressionType.equals(new IntegerType()), true);
    }

    @Test
    public void testExpressionEvaluatorNegativeMoneyExpression() {
        Negative negativeMoneyExpression = new Negative(testMoney, null);
        Value negativeMoneyExpressionValue = negativeMoneyExpression.accept(expressionEvaluator);
        Assert.assertEquals(negativeMoneyExpressionValue.getValue(), BigDecimal.valueOf(-1.00).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testTypeCheckerPositiveIntegerExpression() {
        Positive positiveIntegerExpression = new Positive(testInteger, null);
        Type positiveIntegerExpressionType = typeChecker.visit(positiveIntegerExpression);
        Assert.assertEquals(positiveIntegerExpressionType.equals(new IntegerType()), true);
    }

    @Test
    public void testTypeCheckerPositiveMoneyExpression() {
        Positive positiveMoneyExpression = new Positive(testMoney, null);
        Type positiveMoneyExpressionType = typeChecker.visit(positiveMoneyExpression);
        Assert.assertEquals(positiveMoneyExpressionType.equals(new MoneyType()), true);
    }

    @Test
    public void testExpressionEvaluatorPositiveIntegerExpression() {
        Positive positiveIntegerExpression = new Positive(new MyInteger(-1, null), null);
        Value positiveIntegerExpressionValue = positiveIntegerExpression.accept(expressionEvaluator);
        Assert.assertEquals(positiveIntegerExpressionValue.getValue(), 1);
    }

    @Test
    public void testExpressionEvaluatorPositiveMoneyExpression() {
        Positive positiveMoneyExpression = new Positive(new Money (BigDecimal.valueOf(-1.00),
                new LineNumber(1)), null);
        Value positiveMoneyExpressionValue = positiveMoneyExpression.accept(expressionEvaluator);
        Assert.assertEquals(positiveMoneyExpressionValue.getValue(), BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testTypeCheckerIntegerEQExpression() {
        EQ integerEQExpression = new EQ(testInteger, testInteger, null);
        Type integerEQExpressionType = typeChecker.visit(integerEQExpression);
        Assert.assertEquals(integerEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testTypeCheckerMoneyEQExpression() {
        EQ moneyEQExpression = new EQ(testMoney, testMoney, null);
        Type moneyEQExpressionType = typeChecker.visit(moneyEQExpression);
        Assert.assertEquals(moneyEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerEQExpression() {
        EQ integerEQExpression = new EQ(testInteger, testInteger, null);
        Value integerEQExpressionValue = integerEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(integerEQExpressionValue.getValue(), true);
    }

    @Test
    public void testExpressionEvaluatorMoneyEQExpression() {
        EQ moneyEQExpression = new EQ(testMoney, testMoney, null);
        Value moneyEQExpressionValue = moneyEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(moneyEQExpressionValue.getValue(), true);
    }

    @Test
    public void testTypeCheckerStringEQExpression() {
        EQ stringEQExpression = new EQ(testString, testString, null);
        Type stringEQExpressionType = typeChecker.visit(stringEQExpression);
        Assert.assertEquals(stringEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorStringEQExpression() {
        EQ stringEQExpression = new EQ(testString, testString, null);
        Value stringEQExpressionValue = stringEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(stringEQExpressionValue.getValue(), true);
    }

    @Test
    public void testTypeCheckerBooleanEQExpression() {
        EQ booleanEQExpression = new EQ(testBoolean, testBoolean, null);
        Type booleanEQExpressionType = typeChecker.visit(booleanEQExpression);
        Assert.assertEquals(booleanEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorBooleanEQExpression() {
        EQ booleanEQExpression = new EQ(testBoolean, testBoolean, null);
        Value booleanEQExpressionValue = booleanEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(booleanEQExpressionValue.getValue(), true);
    }

    @Test
    public void testTypeCheckerIntegerNEQExpression() {
        NEQ integerNEQExpression = new NEQ(testInteger, testInteger, null);
        Type integerNEQExpressionType = typeChecker.visit(integerNEQExpression);
        Assert.assertEquals(integerNEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testTypeCheckerMoneyNEQExpression() {
        NEQ moneyNEQExpression = new NEQ(testMoney, testMoney, null);
        Type moneyNEQExpressionType = typeChecker.visit(moneyNEQExpression);
        Assert.assertEquals(moneyNEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerNEQExpression() {
        NEQ integerNEQExpression = new NEQ(testInteger, testInteger, null);
        Value integerNEQExpressionValue = integerNEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(integerNEQExpressionValue.getValue(), false);
    }

    @Test
    public void testExpressionEvaluatorMoneyNEQExpression() {
        NEQ moneyNEQExpression = new NEQ(testMoney, testMoney, null);
        Value moneyNEQExpressionValue = moneyNEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(moneyNEQExpressionValue.getValue(), false);
    }

    @Test
    public void testTypeCheckerStringNEQExpression() {
        NEQ stringNEQExpression = new NEQ(testString, testString, null);
        Type stringNEQExpressionType = typeChecker.visit(stringNEQExpression);
        Assert.assertEquals(stringNEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorStringNEQExpression() {
        NEQ stringNEQExpression = new NEQ(testString, testString, null);
        Value stringNEQExpressionValue = stringNEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(stringNEQExpressionValue.getValue(), false);
    }

    @Test
    public void testTypeCheckerBooleanNEQExpression() {
        NEQ booleanNEQExpression = new NEQ(testBoolean, testBoolean, null);
        Type booleanNEQExpressionType = typeChecker.visit(booleanNEQExpression);
        Assert.assertEquals(booleanNEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorBooleanNEQExpression() {
        NEQ booleanNEQExpression = new NEQ(testBoolean, testBoolean, null);
        Value booleanNEQExpressionValue = booleanNEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(booleanNEQExpressionValue.getValue(), false);
    }

    @Test
    public void testTypeCheckerIntegerGTExpression() {
        GT integerGTExpression = new GT(testInteger, testInteger, null);
        Type integerGTExpressionType = typeChecker.visit(integerGTExpression);
        Assert.assertEquals(integerGTExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testTypeCheckerMoneyGTExpression() {
        GT moneyGTExpression = new GT(testMoney, testMoney, null);
        Type moneyGTExpressionType = typeChecker.visit(moneyGTExpression);
        Assert.assertEquals(moneyGTExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerGTExpression() {
        GT integerGTExpression = new GT(testInteger, testInteger, null);
        Value integerGTExpressionValue = integerGTExpression.accept(expressionEvaluator);
        Assert.assertEquals(integerGTExpressionValue.getValue(), false);
    }

    @Test
    public void testExpressionEvaluatorMoneyGTExpression() {
        GT moneyGTExpression = new GT(testMoney, testMoney, null);
        Value moneyGTExpressionValue = moneyGTExpression.accept(expressionEvaluator);
        Assert.assertEquals(moneyGTExpressionValue.getValue(), false);
    }

    @Test
    public void testTypeCheckerStringGTExpression() {
        GT stringGTExpression = new GT(testString, testString, null);
        Type stringGTExpressionType = typeChecker.visit(stringGTExpression);
        Assert.assertEquals(stringGTExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorStringGTExpression() {
        GT stringGTExpression = new GT(testString, testString, null);
        Value stringGTExpressionValue = stringGTExpression.accept(expressionEvaluator);
        Assert.assertEquals(stringGTExpressionValue.getValue(), false);
    }

    @Test
    public void testTypeCheckerIntegerLTExpression() {
        LT integerLTExpression = new LT(testInteger, testInteger, null);
        Type integerLTExpressionType = typeChecker.visit(integerLTExpression);
        Assert.assertEquals(integerLTExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testTypeCheckerMoneyLTExpression() {
        LT moneyLTExpression = new LT(testMoney, testMoney, null);
        Type moneyLTExpressionType = typeChecker.visit(moneyLTExpression);
        Assert.assertEquals(moneyLTExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerLTExpression() {
        LT integerLTExpression = new LT(testInteger, testInteger, null);
        Value integerLTExpressionValue = integerLTExpression.accept(expressionEvaluator);
        Assert.assertEquals(integerLTExpressionValue.getValue(), false);
    }

    @Test
    public void testExpressionEvaluatorMoneyLTExpression() {
        LT moneyLTExpression = new LT(testMoney, testMoney, null);
        Value moneyLTExpressionValue = moneyLTExpression.accept(expressionEvaluator);
        Assert.assertEquals(moneyLTExpressionValue.getValue(), false);
    }

    @Test
    public void testTypeCheckerStringLTExpression() {
        LT stringLTExpression = new LT(testString, testString, null);
        Type stringLTExpressionType = typeChecker.visit(stringLTExpression);
        Assert.assertEquals(stringLTExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorStringLTExpression() {
        LT stringLTExpression = new LT(testString, testString, null);
        Value stringLTExpressionValue = stringLTExpression.accept(expressionEvaluator);
        Assert.assertEquals(stringLTExpressionValue.getValue(), false);
    }

    @Test
    public void testTypeCheckerIntegerGTEQExpression() {
        GTEQ integerGTEQExpression = new GTEQ(testInteger, testInteger, null);
        Type integerGTEQExpressionType = typeChecker.visit(integerGTEQExpression);
        Assert.assertEquals(integerGTEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testTypeCheckerMoneyGTEQExpression() {
        GTEQ moneyGTEQExpression = new GTEQ(testMoney, testMoney, null);
        Type moneyGTEQExpressionType = typeChecker.visit(moneyGTEQExpression);
        Assert.assertEquals(moneyGTEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerGTEQExpression() {
        GTEQ integerGTEQExpression = new GTEQ(testInteger, testInteger, null);
        Value integerGTEQExpressionValue = integerGTEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(integerGTEQExpressionValue.getValue(), true);
    }

    @Test
    public void testExpressionEvaluatorMoneyGTEQExpression() {
        GTEQ moneyGTEQExpression = new GTEQ(testMoney, testMoney, null);
        Value moneyGTEQExpressionValue = moneyGTEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(moneyGTEQExpressionValue.getValue(), true);
    }

    @Test
    public void testTypeCheckerStringGTEQExpression() {
        GTEQ stringGTEQExpression = new GTEQ(testString, testString, null);
        Type stringGTEQExpressionType = typeChecker.visit(stringGTEQExpression);
        Assert.assertEquals(stringGTEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorStringGTEQExpression() {
        GTEQ stringGTEQExpression = new GTEQ(testString, testString, null);
        Value stringGTEQExpressionValue = stringGTEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(stringGTEQExpressionValue.getValue(), true);
    }

    @Test
    public void testTypeCheckerIntegerLTEQExpression() {
        LTEQ integerLTEQExpression = new LTEQ(testInteger, testInteger, null);
        Type integerLTEQExpressionType = typeChecker.visit(integerLTEQExpression);
        Assert.assertEquals(integerLTEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testTypeCheckerMoneyLTEQExpression() {
        LTEQ moneyLTEQExpression = new LTEQ(testMoney, testMoney, null);
        Type moneyLTEQExpressionType = typeChecker.visit(moneyLTEQExpression);
        Assert.assertEquals(moneyLTEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorIntegerLTEQExpression() {
        LTEQ integerLTEQExpression = new LTEQ(testInteger, testInteger, null);
        Value integerLTEQExpressionValue = integerLTEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(integerLTEQExpressionValue.getValue(), true);
    }

    @Test
    public void testExpressionEvaluatorMoneyLTEQExpression() {
        LTEQ moneyLTEQExpression = new LTEQ(testMoney, testMoney, null);
        Value moneyLTEQExpressionValue = moneyLTEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(moneyLTEQExpressionValue.getValue(), true);
    }

    @Test
    public void testTypeCheckerStringLTEQExpression() {
        LTEQ stringLTEQExpression = new LTEQ(testString, testString, null);
        Type stringLTEQExpressionType = typeChecker.visit(stringLTEQExpression);
        Assert.assertEquals(stringLTEQExpressionType.equals(new BooleanType()), true);
    }

    @Test
    public void testExpressionEvaluatorStringLTEQExpression() {
        LTEQ stringLTEQExpression = new LTEQ(testString, testString, null);
        Value stringLTEQExpressionValue = stringLTEQExpression.accept(expressionEvaluator);
        Assert.assertEquals(stringLTEQExpressionValue.getValue(), true);
    }

}
