package org.ql.typechecker.statement;

import org.junit.Test;
import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.Type;
import org.ql.collector.QuestionCollector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FormQuestionCollectorTest {
    @Test
    public void shouldCollectQuestionsFromForm() {
        List<Statement> statements = new ArrayList<>();
        List<Statement> thenStatements = new ArrayList<>();
        Question firstQuestion = createQuestion();
        Question secondQuestion = createQuestion();
        Question thirdQuestion = createQuestion();
        statements.add(firstQuestion);
        thenStatements.add(secondQuestion);
        thenStatements.add(thirdQuestion);
        statements.add(new IfThen(mock(Expression.class), thenStatements));
        Form form = new Form(mock(Identifier.class), statements);
        QuestionCollector<Form> collector = new FormQuestionCollector(new QuestionVisitor());

        List<Question> actualQuestionList = collector.collect(form);

        assertSame(firstQuestion, actualQuestionList.get(0));
        assertSame(secondQuestion, actualQuestionList.get(1));
        assertSame(thirdQuestion, actualQuestionList.get(2));
    }

    private Question createQuestion() {
        return new Question(mock(Identifier.class), mock(QuestionText.class), mock(Type.class), mock(Expression.class));
    }
}