package org.ql.typechecker;

import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.*;
import org.ql.typechecker.circular_dependencies.CircularDependenciesResolver;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TypeCheckerTest {

    @Test
    public void shouldAddErrorWhenFormNameEmpty() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        typeChecker.visit(new Form(new Identifier(""), new ArrayList<>()), null);

        assertTrue(messages.getErrors().size() == 1);
        assertEquals("Identifier cannot be empty", messages.getErrors().get(0));
    }

    @Test
    public void shouldContainNoErrorsWhenQuestionAdded() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        typeChecker.visit(new Form(new Identifier("test"), new ArrayList<>()), null);

        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldAddErrorWhenDuplicateLabelsAndTypeForQuestion() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        String questionLabel = "example";
        String expectedError = "Question '" + questionLabel + "' has duplicate(s)";

        typeChecker.visit(new Form(new Identifier("exampleForm"), new ArrayList<Statement>() {{
            add(new Question(new Identifier(questionLabel), new QuestionText("example question?"), new BooleanType(), null));
            add(new Question(new Identifier(questionLabel), new QuestionText("example question?"), new BooleanType(), null));
        }}), null);

        assertTrue(messages.getErrors().size() == 2);
        assertEquals(expectedError, messages.getErrors().get(0));
        assertEquals(expectedError, messages.getErrors().get(1));
    }

    @Test
    public void shouldCheckStatementsAndGiveErrors() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        typeChecker.checkStatements(new ArrayList<Statement>() {{
            add(new Question(new Identifier("test"), new QuestionText("example question?"), new BooleanType(), new IntegerLiteral(12)));
            add(new Question(new Identifier("test"), new QuestionText(""), new BooleanType(), null));
        }});

        assertEquals(2, messages.getErrors().size());
    }

    @Test
    public void shouldNotGiveErrorWhenQuestionTypeIsMoneyAndValueTypeIsFloat() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        typeChecker.visit(new Question(new Identifier("test"), new QuestionText("example question?"), new MoneyType(), new DecimalLiteral(new BigDecimal(23.4))), null);

        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveErrorWhenQuestionTypeIsMoneyAndValueTypeIsNonDecimal() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        typeChecker.visit(new Question(new Identifier("test"), new QuestionText("example question?"), new MoneyType(), new BooleanLiteral(true)), null);

        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldThrowExceptionWhenNegationAppliedOnNonBoolean() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Negation negation = new Negation(new StringLiteral("example string"));

        Type actualNegationType = typeChecker.visitNegation(negation, null);

        assertTrue(actualNegationType instanceof UnknownType);
        assertEquals("Type mismatch: expected boolean, but got string", messages.getErrors().get(0));
    }

    @Test
    public void shouldReturnBooleanTypeWhenNegationHasABooleanLiteral() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Negation negation = new Negation(new BooleanLiteral(true));

        Type actualNegationType = typeChecker.visitNegation(negation, null);

        assertTrue(actualNegationType instanceof BooleanType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldReturnLiteralTypes() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        Type actualStringType = typeChecker.visitString(new StringLiteral("example"), null);
        Type actualFloatType = typeChecker.visitDecimal(new DecimalLiteral(new BigDecimal(4.5)), null);
        Type actualBooleanType = typeChecker.visitBoolean(new BooleanLiteral(false), null);
        Type actualIntegerType = typeChecker.visitInteger(new IntegerLiteral(34), null);

        assertNotNull(actualStringType);
        assertNotNull(actualFloatType);
        assertNotNull(actualBooleanType);
        assertNotNull(actualIntegerType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerLiteralWhenIncrementAppliedOnInteger() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        Type actualIntegerType = typeChecker.visitIncrement(new Increment(new IntegerLiteral(3)), null);

        assertTrue(actualIntegerType instanceof IntegerType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldReturnFloatWhenIncrementAppliedOnFloat() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        Type actualFloatType = typeChecker.visitIncrement(new Increment(new DecimalLiteral(new BigDecimal(10.40))), null);

        assertTrue(actualFloatType instanceof FloatType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveUnexpectedTypeErrorWhenIncrementAppliedOnNonIntegerOrFloat() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        Type actualIncrementType = typeChecker.visitIncrement(new Increment(new StringLiteral("example")), null);

        assertTrue(actualIncrementType instanceof UnknownType);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeWhenDecrementAppliedOnInteger() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        Type actualIntegerType = typeChecker.visitDecrement(new Decrement(new IntegerLiteral(3)), null);

        assertTrue(actualIntegerType instanceof IntegerType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldReturnFloatTypeWhenDecrementAppliedOnFloat() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        Type actualFloatType = typeChecker.visitDecrement(new Decrement(new DecimalLiteral(new BigDecimal(10.40))), null);

        assertTrue(actualFloatType instanceof FloatType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveUnexpectedTypeErrorWhenDecrementAppliedOnNonIntegerOrFloat() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        Type actualType = typeChecker.visitDecrement(new Decrement(new StringLiteral("example")), null);

        assertTrue(actualType instanceof UnknownType);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldGiveUndefinedIdentifierErrorWhenIdentifierDoesNotExist() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        Type actualParameterType = typeChecker.visitParameter(new Parameter(new Identifier("example")), null);

        assertEquals(1, messages.getErrors().size());
        assertTrue(actualParameterType instanceof UnknownType);
    }

    @Test
    public void shouldReturnParameterWhenIdentifierExists() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.declare(new Identifier("example"), new StringType());
        Messages messages = new Messages();

        TypeChecker typeChecker = new TypeChecker(messages, symbolTable, new CircularDependenciesResolver());

        Type actualParameterType = typeChecker.visitParameter(new Parameter(new Identifier("example")), null);

        assertTrue(actualParameterType instanceof StringType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalOr() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalOrType = typeChecker.visitOr(actualLogicalOr, null);

        assertNotNull(actualLogicalOrType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLogicalOr() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new IntegerLiteral(12));

        typeChecker.visitOr(actualLogicalOr, null);

        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorNonBooleanTypesAreUsedInLogicalOr() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        LogicalOr actualLogicalOr = new LogicalOr(new IntegerLiteral(233), new IntegerLiteral(12));

        typeChecker.visitOr(actualLogicalOr, null);

        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThanOrEquals() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanOrEqualType = typeChecker.visitLowerThanOrEqual(actualLowerThanOrEqual, null);

        assertNotNull(actualLowerThanOrEqualType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLowerThanOrEqual() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        typeChecker.visitLowerThanOrEqual(actualLowerThanOrEqual, null);

        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForEquals() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Equals actualEqual = new Equals(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualEqualType = typeChecker.visitEquals(actualEqual, null);

        assertNotNull(actualEqualType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInEquals() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Equals actualEquals = new Equals(new BooleanLiteral(true), new IntegerLiteral(12));

        typeChecker.visitEquals(actualEquals, null);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThan() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanType = typeChecker.visitGreaterThan(actualGreaterThan, null);

        assertNotNull(actualGreaterThanType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInGreaterThan() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new IntegerLiteral(12));

        typeChecker.visitGreaterThan(actualGreaterThan, null);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeForAddition() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Addition actualAddition = new Addition(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualAdditionType = typeChecker.visitAddition(actualAddition, null);

        assertTrue(actualAdditionType instanceof IntegerType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInAddition() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Addition actualAddition = new Addition(new BooleanLiteral(true), new IntegerLiteral(12));

        Type actualAdditionType = typeChecker.visitAddition(actualAddition, null);

        assertTrue(actualAdditionType instanceof UnknownType);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeForDivision() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Division actualDivision = new Division(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualDivisionType = typeChecker.visitDivision(actualDivision, null);

        assertTrue(actualDivisionType instanceof IntegerType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInDivision() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Division actualDivision = new Division(new BooleanLiteral(true), new IntegerLiteral(12));

        Type actualDivisionType = typeChecker.visitDivision(actualDivision, null);

        assertTrue(actualDivisionType instanceof UnknownType);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThanOrEqual() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanOrEqualType = typeChecker.visitGreaterThanOrEqual(actualGreaterThanOrEqual, null);

        assertNotNull(actualGreaterThanOrEqualType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInGreaterThanOrEqual() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        typeChecker.visitGreaterThanOrEqual(actualGreaterThanOrEqual, null);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThan() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanType = typeChecker.visitLowerThan(actualLowerThan, null);

        assertNotNull(actualLowerThanType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLowerThan() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new IntegerLiteral(12));

        typeChecker.visitLowerThan(actualLowerThan, null);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalAnd() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalAndType = typeChecker.visitAnd(actualLogicalAnd, null);

        assertNotNull(actualLogicalAndType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLogicalAnd() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new IntegerLiteral(12));

        typeChecker.visitAnd(actualLogicalAnd, null);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForNotEqual() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualNotEqualType = typeChecker.visitNotEqual(actualNotEqual, null);

        assertNotNull(actualNotEqualType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInNotEqual() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        typeChecker.visitNotEqual(actualNotEqual, null);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeOnGroupedRelationalExpression() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Group actualGroup = new Group(new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(true)));

        Type actualGroupType = typeChecker.visitGroup(actualGroup, null);

        assertTrue(actualGroupType instanceof BooleanType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeForProduct() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Product actualProduct = new Product(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualProductType = typeChecker.visitProduct(actualProduct, null);

        assertTrue(actualProductType instanceof IntegerType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInProduct() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Product actualProduct = new Product(new BooleanLiteral(true), new IntegerLiteral(12));

        Type actualProductType = typeChecker.visitProduct(actualProduct, null);

        assertTrue(actualProductType instanceof UnknownType);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldReturnWhenIntegerTypeForSubtraction() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        Type actualSubtractionType = typeChecker.visitSubtraction(new Subtraction(new IntegerLiteral(12), new IntegerLiteral(4)), null);

        assertTrue(actualSubtractionType instanceof IntegerType);
        assertEquals(0, messages.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesUsedForSubtraction() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());
        Subtraction actualSubtraction = new Subtraction(new IntegerLiteral(12), new StringLiteral("example"));

        Type actualSubtractionType = typeChecker.visitSubtraction(actualSubtraction, null);

        assertTrue(actualSubtractionType instanceof UnknownType);
        assertEquals(1, messages.getErrors().size());
    }

    @Test
    public void shouldGiveCircularDependencyErrorWhenSuchAreThere() {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages, new SymbolTable(), new CircularDependenciesResolver());

        typeChecker.visit(new Form(new Identifier("Form"), new ArrayList<Statement>() {{
            add(new Question(new Identifier("first"), new QuestionText("label1"), new BooleanType(), new Parameter(new Identifier("second"))));
            add(new Question(new Identifier("second"), new QuestionText("label2"), new BooleanType(), new Parameter(new Identifier("first"))));
            add(new Question(new Identifier("third"), new QuestionText("label2"), new BooleanType(), null));
        }}), null);

        assertEquals(2, messages.getErrors().size());
        assertTrue(messages.getErrors().contains("Circular dependency detected for question 'second'"));
        assertTrue(messages.getErrors().contains("Circular dependency detected for question 'first'"));
    }
}
