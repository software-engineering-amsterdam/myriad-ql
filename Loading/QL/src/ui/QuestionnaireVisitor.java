package ui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.Block;
import ast.BlockItem;
import ast.ComputedQuestion;
import ast.ExpressionVisitor;
import ast.Form;
import ast.FormVisitor;
import ast.Question;
import ast.Statement;
import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.atom.IntegerAtom;
import ast.atom.StringAtom;
import ast.expression.AddExpression;
import ast.expression.AndExpression;
import ast.expression.BinaryExpression;
import ast.expression.DivExpression;
import ast.expression.EqExpression;
import ast.expression.GEqExpression;
import ast.expression.GExpression;
import ast.expression.IdExpression;
import ast.expression.LEqExpression;
import ast.expression.LExpression;
import ast.expression.MinusExpression;
import ast.expression.MulExpression;
import ast.expression.NEqExpression;
import ast.expression.NotExpression;
import ast.expression.OrExpression;
import ast.expression.PlusExpression;
import ast.expression.SubExpression;
import ast.expression.UnaryExpression;
import ast.type.BooleanType;
import ast.type.Type;
import evaluation.Environment;
import evaluation.Evaluator;
import value.BoolValue;
import value.Value;

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
