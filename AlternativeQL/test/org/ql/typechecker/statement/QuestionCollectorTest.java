package org.ql.typechecker.statement;

import org.junit.Test;
import org.ql.ast.Expression;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.IntegerType;
import org.ql.collection.QuestionCollector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class QuestionCollectorTest {
    @Test
    public void shouldAddSingleQuestionToAList() {
        Question expectedQuestion = new Question(
                new Identifier("question"), new QuestionText("a simple text"), new BooleanType(), null);

        StatementVisitor<List<Question>> visitor = new QuestionCollector();
        List<Question> actualQuestionList = visitor.visit(expectedQuestion);
        Question actualQuestion = actualQuestionList.get(0);

        assertEquals(1, actualQuestionList.size());
        assertSame(expectedQuestion, actualQuestion);
    }

    @Test
    public void shouldCollectTwoQuestionsFromIfThenStatement() {
        Question firstQuestion = new Question(
                new Identifier("question"), new QuestionText("a simple text"), new BooleanType(), null);
        Question secondQuestion = new Question(
                new Identifier("question2"), new QuestionText("another simple text"), new IntegerType(), null);

        List<Statement> questions = new ArrayList<>();
        questions.add(firstQuestion);
        questions.add(secondQuestion);

        IfThen ifThen = new IfThen(mock(Expression.class), questions);
        StatementVisitor<List<Question>> visitor = new QuestionCollector();

        List<Question> actualQuestionList = visitor.visit(ifThen);

        assertEquals(2, actualQuestionList.size());
        assertSame(firstQuestion, actualQuestionList.get(0));
        assertSame(secondQuestion, actualQuestionList.get(1));
    }

    @Test
    public void shouldCollectTwoQuestionsFromIfThenElseStatement() {
        Question firstQuestion = new Question(
                new Identifier("question"), new QuestionText("a simple text"), new BooleanType(), null);
        Question secondQuestion = new Question(
                new Identifier("question2"), new QuestionText("another simple text"), new IntegerType(), null);

        List<Statement> thenQuestions = new ArrayList<>();
        List<Statement> elseQuestions = new ArrayList<>();
        thenQuestions.add(firstQuestion);
        elseQuestions.add(secondQuestion);

        IfThenElse ifThenElse = new IfThenElse(mock(Expression.class), thenQuestions, elseQuestions);
        StatementVisitor<List<Question>> visitor = new QuestionCollector();

        List<Question> actualQuestionList = visitor.visit(ifThenElse);

        assertEquals(2, actualQuestionList.size());
        assertSame(firstQuestion, actualQuestionList.get(0));
        assertSame(secondQuestion, actualQuestionList.get(1));
    }
}