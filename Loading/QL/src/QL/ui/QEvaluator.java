package QL.ui;

import QL.ast.ComputedQuestion;
import QL.ast.IfElseStatement;
import QL.ast.Question;
import QL.ast.Statement;
import QL.evaluation.Evaluator;
import QL.ui.field.Field;
import QL.value.BoolValue;
import QL.value.Value;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;


public class QEvaluator extends Evaluator {

	private final List<Row> visibleRows;
    private final Environment answers;
    private final Notifier notifier;

	public QEvaluator(Environment answers, Notifier notifier) {
		super(answers);
		this.answers = answers;
		this.visibleRows = new ArrayList<>();
		this.notifier = notifier;
	}
	
	List<Row> getVisibleRows() {
		return visibleRows;
	}
	
    @Override
    public void visit(Question question) {
    	    	
        visibleRows.add(createRow(question));
    }
    
    @Override
    public void visit(ComputedQuestion question) {
        Value value = question.getComputedQuestion().accept(this);
        answers.addAnswer(question.getVariable(), value);

        visibleRows.add(createRow(question));
    }

    private Row createRow(Question question) {

        Value answer = getAnswer(question);

        Field field = answer.getField(question.getVariable(), notifier, answer);

        return new Row(question.getVariable(), new Label(question.getLabel()), field);
    }

    private Value getAnswer(Question question) {

        if (!answers.isAnswered(question.getVariable())) {
            return getDefaultAnswer(question);
        }

        return answers.getAnswer(question.getVariable());
    }

    private Value getDefaultAnswer(Question question) {
        return question.getType().accept(this);
    }

    @Override
    public void visit(Statement statement) {
        Value value = statement.getExpression().accept(this);

        if (((BoolValue) value).getValue()) {
        	statement.getBlock().accept(this);
        }
    }

    @Override
    public void visit(IfElseStatement statement) {
        Value value = statement.getExpression().accept(this);

        if (((BoolValue) value).getValue()) {
        	statement.getBlock().accept(this);
        } else {
            statement.getElseBlock().accept(this);
        }
    }
}
