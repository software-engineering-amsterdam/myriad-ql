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

import java.util.ArrayList;
import java.util.List;


public class QEvaluator extends Evaluator {

	private final List<Row> visibleRows;
    private final Environment environment;

	public QEvaluator(Environment environment) {
		super(environment);
		this.environment = environment;
		this.visibleRows = new ArrayList<>();
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
        environment.addAnswer(question.getName(), value);

        visibleRows.add(createRow(question));
    }

    private Row createRow(Question question) {

        Field field = environment.getField(question.getName());

        return new Row(question.getName(), new Label(question.getLabel()), field);
    }
    
    private Row createRow(ComputedQuestion question) {
    	
    	Field field = environment.getField(question.getName());
    	
    	field.setValue(getAnswer(question));
        
        return new Row(question.getName(), new Label(question.getLabel()), field);
    }

    private Value getAnswer(Question question) {

        if (!environment.isAnswered(question.getName())) {
            return getDefaultAnswer(question);
        }

        return environment.getAnswer(question.getName());
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
