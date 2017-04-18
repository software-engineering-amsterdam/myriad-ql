package com.matthewchapman.ql.app;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.parsing.ASTBuilder;
import com.matthewchapman.ql.visitors.StatementVisitor;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 02/03/2017.
 * <p>
 * Tests building of ASTs
 */
public class ASTBuilderTest implements StatementVisitor<Void, String> {

    private final ASTBuilder parser = new ASTBuilder();
    private String testInput;
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

        final int EXPECTED_STATEMENTS = 11;
        final int EXPECTED_QUESTIONS = 5;
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
}