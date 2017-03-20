package org.ql.evaluator;

import org.junit.Test;
import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Statement;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.Addition;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.statement.ComputableQuestion;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.IntegerType;
import org.ql.evaluator.value.IntegerValue;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class ConditionEvaluatorTest {

    @Test
    public void shouldMakeValueTableWithBranchedQuestions() {
        ValueTable valueTable = new ValueTable();
        valueTable.declare(new Identifier("first"), new IntegerValue(12));
        valueTable.declare(new Identifier("second"), new IntegerValue(48));
        valueTable.declare(new Identifier("third"), new IntegerValue(48));

        ConditionEvaluator visitor = new ConditionEvaluator();
        List<Question> visibleElements = visitor.visitForm(new Form(new Identifier("Example"), new ArrayList<Statement>() {{
            add(new IfThenElse(new BooleanLiteral(true), new ArrayList<Statement>() {{
                add(new ComputableQuestion(new Identifier("first"), new QuestionLabel("ComputableQuestion"), new IntegerType(), new IntegerLiteral(12)));
            }}, new ArrayList<Statement>() {{
                add(new ComputableQuestion(new Identifier("second"), new QuestionLabel("Question2"), new IntegerType(), new Parameter(new Identifier("third"))));
                add(new ComputableQuestion(new Identifier("third"), new QuestionLabel("Question2"), new IntegerType(), new Addition(
                        new IntegerLiteral(13), new IntegerLiteral(35)
                )));
            }}));
        }}), valueTable);
    }
}