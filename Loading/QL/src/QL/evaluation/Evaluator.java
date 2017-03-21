package QL.evaluation;

import QL.ast.*;
import QL.ast.atom.BoolAtom;
import QL.ast.atom.IntegerAtom;
import QL.ast.atom.StringAtom;
import QL.ast.expression.*;
import QL.ast.type.BooleanType;
import QL.ast.type.IntegerType;
import QL.ast.type.StringType;
import QL.ast.type.UnknownType;
import QL.ui.Environment;
import QL.value.BoolValue;
import QL.value.IntegerValue;
import QL.value.StringValue;
import QL.value.Value;

public class Evaluator implements FormVisitor, QL.ast.ExpressionVisitor<Value>, TypeVisitor<Value> {

	private final Environment environment;

	protected Evaluator(Environment environment) {
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
        question.getComputedQuestion().accept(this);
    }

    @Override
    public void visit(Statement statement) {
	    statement.getExpression().accept(this);
        statement.getBlock().accept(this);
    }

    @Override
    public void visit(IfElseStatement statement) {
	    statement.getExpression().accept(this);
        statement.getBlock().accept(this);
        statement.getElseBlock().accept(this);
    }

    @Override
    public Value visit(AddExpr expr) {
        return expr.getLhs().accept(this).add(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(AndExpr expr) {
        return expr.getLhs().accept(this).and(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(DivExpr expr) {
        return expr.getLhs().accept(this).div(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(EqExpr expr) {
        return expr.getLhs().accept(this).eq(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(GEqExpr expr) {
        return expr.getLhs().accept(this).greaterEq(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(GExpr expr) {
        return expr.getLhs().accept(this).greater(expr.getRhs().accept(this));
    }

    @Override
	public Value visit(IdExpr id) {
    	    	
		if (!environment.isAnswered(id.getName())) {
			return environment.getType(id.getName()).accept(this);
		}

		return environment.getAnswer(id.getName());
	}

    @Override
    public Value visit(LEqExpr expr) {
        return expr.getLhs().accept(this).lessEq(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(LExpr expr) {
        return expr.getLhs().accept(this).less(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(MinusExpr expr) {
        return expr.getLhs().accept(this).min();
    }

    @Override
    public Value visit(MulExpr expr) {
        return expr.getLhs().accept(this).mul(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(NEqExpr expr) {
        return expr.getLhs().accept(this).notEq(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(NotExpr expr) {
        return expr.getLhs().accept(this).not();
    }

    @Override
    public Value visit(OrExpr expr) {
        return expr.getLhs().accept(this).or(expr.getRhs().accept(this));
    }

    @Override
    public Value visit(PlusExpr expr) {
        return expr.getLhs().accept(this).plus();
    }

    @Override
    public Value visit(SubExpr expr) {
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

	@Override
	public Value visit(BooleanType type) {
		return new BoolValue();
	}

	@Override
	public Value visit(IntegerType type) {
		return new IntegerValue();
	}

	@Override
	public Value visit(StringType type) {
		return new StringValue();
	}

	@Override
	public Value visit(UnknownType unknownType) {
        throw new RuntimeException("Should never visit an unknown type during evaluation");
	}
}


