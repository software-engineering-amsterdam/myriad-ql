package ui;
import java.util.ArrayList;
import java.util.List;

import ast.ComputedQuestion;
import ast.Question;
import ast.Statement;
import evaluation.Environment;
import evaluation.Evaluator;
import value.BoolValue;
import value.Value;


public class QEvaluator extends Evaluator {

	private List<Row> activeQuestions; // TODO Row String and type?
    private evaluation.Environment answers;

	public QEvaluator(Environment answers) {
		super(answers);
		this.answers = answers;
		this.activeQuestions = new ArrayList<>();
	}
	
	public List<Row> getActiveQuestions() {
		return activeQuestions;
	}
	
    @Override
    public void visit(Question question) {
        activeQuestions.add(new Row(question.getVariable(),
        		question.getLabel(), question.getType()));
    }
    
    @Override
    public void visit(ComputedQuestion question) {
        System.out.println("Evaluator: computed question");
        Value value = question.getComputedQuestion().accept(this);

        Row q = new Row(question.getVariable(),
                question.getLabel(), question.getType());

        // TODO only works with integers...
        if (value.isSet()) {
            answers.addAnswer(question.getVariable(), value);
        }

        activeQuestions.add(q);
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
        if (((BoolValue) value).getValue()) { // TODO check booltype?
            System.out.println("Evaluator: statement, value = " + ((BoolValue) value).getValue());
        	statement.getBlock().accept(this);
        }
    }
}
