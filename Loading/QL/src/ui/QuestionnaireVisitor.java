package ui;
import java.util.ArrayList;
import java.util.List;

import ast.ComputedQuestion;
import ast.Question;
import ast.Statement;
import ast.atom.Atom;
import evaluation.Environment;
import evaluation.Evaluator;

// Or Statement Visitor
public class QuestionnaireVisitor extends Evaluator {

	private List<QuestionnaireQuestion> activeQuestions; // TODO QQuestion String and type?

	public QuestionnaireVisitor(Environment answers) {
		super(answers);
		this.activeQuestions = new ArrayList<>();
	}
	
	public List<QuestionnaireQuestion> getActiveQuestions() {
		return activeQuestions;
	}
	
    @Override
    public void visit(Question question) {
        activeQuestions.add(new QuestionnaireQuestion(question.getVariable(),
        		question.getLabel(), question.getType()));
    }
    
    @Override
    public void visit(ComputedQuestion question) {
        Atom atom = question.getComputedQuestion().accept(this);
        activeQuestions.add(new QuestionnaireQuestion(question.getVariable(),
        		question.getLabel(), question.getType()));
        // TODO what to do with the answer of a computed question?
    }

    @Override
    public void visit(Statement statement) {
        Atom atom = statement.getExpression().accept(this);
        // TODO nicer check for emptyAtom?
        if (atom != null && atom.getValue()) { // TODO check booltype?
            System.out.println("The value of atom: " + atom.getValue());
        	statement.getBlock().accept(this);
        }
    }
}
