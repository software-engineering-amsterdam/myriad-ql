package ui;
import java.util.ArrayList;
import java.util.List;

import ast.ComputedQuestion;
import ast.IfElseStatement;
import ast.Question;
import ast.Statement;
import evaluation.Environment;
import evaluation.Evaluator;
import ui.field.Field;
import value.BoolValue;
import value.Value;


public class QEvaluator extends Evaluator {

	private List<Row> activeQuestions; // TODO Row String and type?
    private evaluation.Environment answers;
    private Notifier notifier;

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
    	
        System.out.println("Evaluator: computed question");
        Value value = question.getComputedQuestion().accept(this);

        // TODO only works with integers...
        if (value.isSet()) {
            answers.addAnswer(question.getVariable(), value);
        }

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
        
        // TODO assert atom != null
        if (value == null) {
//        	throw new AssertionError("The operation " + statement.getExpression().getClass() 
//        			+ " ")
        }
        
        // TODO nicer check for emptyAtom?
        if (value.isSet() && ((BoolValue) value).getValue()) { // TODO check booltype?
            System.out.println("Evaluator: statement, value = " + ((BoolValue) value).getValue());
        	statement.getBlock().accept(this);
        }
    }

    @Override
    public void visit(IfElseStatement statement) {
        Value value = statement.getExpression().accept(this);

        // TODO assert atom != null
        if (value == null) {
        	throw new AssertionError("The operation " + statement.getExpression().getClass()
        			+ " ");
        }

        // TODO nicer check for emptyAtom?
        if (value.isSet() && ((BoolValue) value).getValue()) { // TODO check booltype?
            System.out.println("Evaluator: statement, value = " + ((BoolValue) value).getValue());
        	statement.getBlock().accept(this);
        } else {
            System.out.println("Evaluator: visit elseblock");
            statement.getElseBlock().accept(this);
        }
    }
}
