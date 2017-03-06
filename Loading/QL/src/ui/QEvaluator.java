package ui;
import java.util.ArrayList;
import java.util.List;

import ast.ComputedQuestion;
import ast.Question;
import ast.Statement;
import ast.atom.Atom;
import evaluation.Environment;
import evaluation.Evaluator;
import value.Value;


public class QEvaluator extends Evaluator {

	private List<QQuestion> activeQuestions; // TODO QQuestion String and type?

	public QEvaluator(Environment answers) {
		super(answers);
		this.activeQuestions = new ArrayList<>();
	}
	
	public List<QQuestion> getActiveQuestions() {
		return activeQuestions;
	}
	
    @Override
    public void visit(Question question) {
        activeQuestions.add(new QQuestion(question.getVariable(),
        		question.getLabel(), question.getType()));
    }
    
    @Override
    public void visit(ComputedQuestion question) {
        System.out.println("Evaluator: computed question");
        Atom atom = question.getComputedQuestion().accept(this);

        QQuestion q = new QQuestion(question.getVariable(),
                question.getLabel(), question.getType());

        // TODO what to do with the answer of a computed question?
        if (atom.isSet()) {
            q.setAnswer(new Value(atom.getNumber()));
        }

        activeQuestions.add(q);
    }

    @Override
    public void visit(Statement statement) {
        Atom atom = statement.getExpression().accept(this);
        
        // TODO
        if (atom == null) {
//        	throw new AssertionError("The operation " + statement.getExpression().getClass() 
//        			+ " ")
        }
        
        // TODO nicer check for emptyAtom?
        if (atom != null && atom.getValue()) { // TODO check booltype?
            System.out.println("The value of atom: " + atom.getValue());
        	statement.getBlock().accept(this);
        }
    }
}
