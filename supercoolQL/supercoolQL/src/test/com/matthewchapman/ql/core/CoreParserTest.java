package com.matthewchapman.ql.core;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.atomic.*;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.validation.QLVisitor;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 02/03/2017.
 */
public class CoreParserTest implements QLVisitor<Void> {

    private String testInput;
    private CoreParser parser = new CoreParser();
    private int questionsCount = 0;
    private int ifStatementCount = 0;
    private int ifElseStatementCount = 0;

    @Before
    public void setUp() {
        FileReader reader = new FileReader();
        testInput = reader.readFile(new File("res/test.txt"));
    }

    @Test
    public void buildQLAST() {
        Form form = parser.buildQLAST(testInput);

        final int EXPECTED_STATEMENTS = 9;
        final int EXPECTED_QUESTIONS = 6;
        final int EXPECTED_IFS = 2;
        final int EXPECTED_IF_ELSES = 1;

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        assertEquals(EXPECTED_STATEMENTS, form.getStatements().size());
        assertEquals(EXPECTED_QUESTIONS, questionsCount);
        assertEquals(EXPECTED_IFS, ifStatementCount);
        assertEquals(EXPECTED_IF_ELSES, ifElseStatementCount);
    }

    @Override
    public Void visit(Question question, String context) {
        ++questionsCount;
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {
        ++ifStatementCount;
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement, String context) {
        ++ifElseStatementCount;
        return null;
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion, String context) {
        return null;
    }

    @Override
    public Void visit(Parameter parameter, String context) {
        return null;
    }

    @Override
    public Void visit(ParameterGroup parameterGroup, String context) {
        return null;
    }

    @Override
    public Void visit(StringLiteral stringLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(Addition addition, String context) {
        return null;
    }

    @Override
    public Void visit(Division division, String context) {
        return null;
    }

    @Override
    public Void visit(Equal equal, String context) {
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan, String context) {
        return null;
    }

    @Override
    public Void visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        return null;
    }

    @Override
    public Void visit(LessThan lessThan, String context) {
        return null;
    }

    @Override
    public Void visit(LessThanEqualTo lessThanEqualTo, String context) {
        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd, String context) {
        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr, String context) {
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication, String context) {
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual, String context) {
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction, String context) {
        return null;
    }

    @Override
    public Void visit(Negation negation, String context) {
        return null;
    }

    @Override
    public Void visit(BooleanType booleanType, String context) {
        return null;
    }

    @Override
    public Void visit(IntegerType integerType, String context) {
        return null;
    }

    @Override
    public Void visit(StringType stringType, String context) {
        return null;
    }
}