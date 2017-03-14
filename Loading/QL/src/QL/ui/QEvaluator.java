package QL.ui;
import java.util.ArrayList;
import java.util.List;

import QL.ast.ComputedQuestion;
import QL.ast.IfElseStatement;
import QL.ast.Question;
import QL.ast.Statement;
import QL.Environment;
import QL.evaluation.Evaluator;
import QL.ui.field.Field;
import QL.value.BoolValue;
import QL.value.Value;


public class QEvaluator extends Evaluator {

	private final List<Row> activeQuestions;
    private final Environment answers;
    private final Notifier notifier;

	public QEvaluator(Environment answers, Notifier notifier) {
		super(answers);
		this.answers = answers;
		this.activeQuestions = new ArrayList<>();
		this.notifier = notifier;
	}
	
	public List<Row> getActiveQuestions() {
		return activeQuestions;
	}
	
    @Override
    public void visit(Question question) {
    	    	
        activeQuestions.add(createRow(question));
    }
    
    @Override
    public void visit(ComputedQuestion question) {
        Value value = question.getComputedQuestion().accept(this);
        answers.addAnswer(question.getVariable(), value);

        activeQuestions.add(createRow(question));
    }
    
    private Row createRow(Question question) {
        Value answer = answers.getAnswer(question.getVariable());
        Field field = question.getType().getField(question.getVariable(), notifier, answer);
        
        return new Row(question.getVariable(), question.getLabel(), question.getType(), field);
    }

    @Override
    public void visit(Statement statement) {
        Value value = statement.getExpression().accept(this);

        if (value.isSet() && ((BoolValue) value).getValue()) {
        	statement.getBlock().accept(this);
        }
    }

    @Override
    public void visit(IfElseStatement statement) {
        Value value = statement.getExpression().accept(this);

        if (value.isSet() && ((BoolValue) value).getValue()) {
        	statement.getBlock().accept(this);
        } else {
            statement.getElseBlock().accept(this);
        }
    }
}
