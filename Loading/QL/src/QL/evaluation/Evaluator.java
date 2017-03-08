package QL.evaluation;

import QL.ast.*;
import QL.ast.atom.BoolAtom;
import QL.ast.atom.IntegerAtom;
import QL.ast.atom.StringAtom;
import QL.ast.expression.*;
import QL.value.BoolValue;
import QL.value.IntegerValue;
import QL.value.StringValue;
import QL.value.Value;

public class Evaluator implements FormVisitor, QL.ast.ExpressionVisitor<Value> {

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
    public void visit(Question question) {}

    @Override
    public void visit(ComputedQuestion question) {
        Value value = question.getComputedQuestion().accept(this);
    }

    @Override
    public void visit(Statement statement) {
        Value value = statement.getExpression().accept(this);
        // Add to environment
        statement.getBlock().accept(this);
    }

    @Override
    public void visit(IfElseStatement statement) {
        Value value = statement.getExpression().accept(this);
        // Add to environment
        statement.getBlock().accept(this);
        statement.getElseBlock().accept(this);
    }

    @Override
    public Value visit(AddExpression expr) {
        return expr.getLhs().accept(this).add(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(AndExpression expr) {
        return expr.getLhs().accept(this).and(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(DivExpression expr) {
        return expr.getLhs().accept(this).div(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(EqExpression expr) {
        return expr.getLhs().accept(this).eq(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(GEqExpression expr) {
        return expr.getLhs().accept(this).greaterEq(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(GExpression expr) {
        return expr.getLhs().accept(this).greater(expr.getRhs().accept(this));
    }

    @Override
	public Value visit(IdExpression id) {

		if (!environment.isAnswered(id.getName())) {
			return environment.getType(id.getName()).getValue();
		}

		return environment.getAnswer(id.getName());
	}

    @Override
    public Value visit(LEqExpression expr) {
        return expr.getLhs().accept(this).lessEq(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(LExpression expr) {
        return expr.getLhs().accept(this).less(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(MinusExpression expr) {
        return expr.getLhs().accept(this).min();
    }

    @Override
    public Value visit(MulExpression expr) {
        return expr.getLhs().accept(this).mul(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(NEqExpression expr) {
        return expr.getLhs().accept(this).notEq(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(NotExpression expr) {
        return expr.getLhs().accept(this).not();
    }

    @Override
    public Value visit(OrExpression expr) {
        return expr.getLhs().accept(this).or(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(PlusExpression expr) {
        return expr.getLhs().accept(this).plus();
    }

    @Override
    public Value visit(SubExpression expr) {
        return expr.getLhs().accept(this).sub(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(BoolAtom expr) {
        return new BoolValue(expr.getAtom());
    }

    @Override
    public Value visit(IntegerAtom expr) {
        return new IntegerValue(expr.getAtom());
    }

    @Override
    public Value visit(StringAtom expr) {
	    return new StringValue(expr.getAtom());
    }
}


