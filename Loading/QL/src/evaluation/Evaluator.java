package evaluation;

import ast.*;
import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.atom.EmptyAtom;
import ast.atom.IntegerAtom;
import ast.atom.StringAtom;
import ast.expression.*;
import ast.type.BooleanType;
import ast.type.Type;
import value.EmptyValue;
import value.Value;

public class Evaluator implements FormVisitor, ast.ExpressionVisitor<Atom> {

	private final Environment environment;

	public Evaluator(Environment environment) {
		this.environment = environment;
	}

    @Override
    public void visit(Form form) {
        form.getBlock().accept(this);
    }

    @Override
    public void visit(Block block) {

        for (BlockItem blockItem : block.getBlockItems()) {
            blockItem.accept(this);
        }
    }

    @Override
    public void visit(BlockItem blockItem) {
        blockItem.accept(this);
    }

    @Override
    public void visit(Question question) {
    	// TODO default value when question is not answered
//    	if (!environment.isAnswered(question.getVariable())) {
//    		environment.addAnswer(question.getVariable(), new EmptyValue());
//    	}
    }

    @Override
    public void visit(ComputedQuestion question) {
        Atom atom = question.getComputedQuestion().accept(this);
        // TODO add computed answer to environment
//        if (!environment.isAnswered(question.getVariable())) {
//        	environment.addAnswer(question.getVariable(), new EmptyValue());
//        }// Add to environment
    }

    @Override
    public void visit(Statement statement) {
        Atom atom = statement.getExpression().accept(this);
        // Add to environment
        statement.getBlock().accept(this); // TODO circulair dependencies?
    }

    @Override
    public Atom visit(AddExpression expr) {
        return expr.getLhs().accept(this).add(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(AndExpression expr) {
        return expr.getLhs().accept(this).and(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(DivExpression expr) {
        return expr.getLhs().accept(this).div(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(EqExpression expr) {
        return expr.getLhs().accept(this).eq(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(GEqExpression expr) {
        return expr.getLhs().accept(this).greaterEq(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(GExpression expr) {
        return expr.getLhs().accept(this).greater(expr.getRhs().accept(this));
    }

    @Override
	public Atom visit(IdExpression id) {

		if (!environment.isAnswered(id.getName())) {
			// TODO throw
			// System.out.println("The variable: " + id.getName() + " is not defined before use.\n");
			return new EmptyAtom(0);
		}
		// TODO change!
		// System.out.println("Returns always true");
		// TODO change getValueGetValue
		// TODO change ID is not always a boolAtom!
		// return new BoolAtom(true, 0);
		// return new BoolAtom(environment.getAnswer(id.getName()).getValue().getValue(), 0);
		return environment.getAnswer(id.getName()).getValue();
	}

    @Override
    public Atom visit(LEqExpression expr) {
        return expr.getLhs().accept(this).lessEq(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(LExpression expr) {
        return expr.getLhs().accept(this).less(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(MinusExpression expr) {
        return expr.getLhs().accept(this).min();
    }

    @Override
    public Atom visit(MulExpression expr) {
        return expr.getLhs().accept(this).mul(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(NEqExpression expr) {
        return expr.getLhs().accept(this).notEq(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(NotExpression expr) {
        return expr.getLhs().accept(this).not();
    }

    @Override
    public Atom visit(OrExpression expr) {
        return expr.getLhs().accept(this).or(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(PlusExpression expr) {
        return expr.getLhs().accept(this).plus();
    }

    @Override
    public Atom visit(SubExpression expr) {
        return expr.getLhs().accept(this).sub(expr.getRhs().accept(this));
    }

    @Override
    public Atom visit(BoolAtom expr) {
        return expr; // TODO
    }

    @Override
    public Atom visit(IntegerAtom expr) {
        return expr;
    }

    @Override
    public Atom visit(StringAtom expr) {
        return expr;
    }

	@Override
	public Atom visit(EmptyAtom expr) {
		return expr;
	}
}


