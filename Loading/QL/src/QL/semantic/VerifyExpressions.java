package QL.semantic;


import QL.Error;
import QL.ast.*;
import QL.ast.atom.BoolAtom;
import QL.ast.atom.IntegerAtom;
import QL.ast.atom.StringAtom;
import QL.ast.expression.AddExpression;
import QL.ast.expression.AndExpression;
import QL.ast.expression.DivExpression;
import QL.ast.expression.EqExpression;
import QL.ast.expression.GEqExpression;
import QL.ast.expression.GExpression;
import QL.ast.expression.IdExpression;
import QL.ast.expression.LEqExpression;
import QL.ast.expression.LExpression;
import QL.ast.expression.MinusExpression;
import QL.ast.expression.MulExpression;
import QL.ast.expression.NEqExpression;
import QL.ast.expression.NotExpression;
import QL.ast.expression.OrExpression;
import QL.ast.expression.PlusExpression;
import QL.ast.expression.SubExpression;
import QL.ast.type.BooleanType;
import QL.ast.type.IntegerType;
import QL.ast.type.StringType;
import QL.ast.type.Type;
import QL.ast.type.UnknownType;

// Checks unreferenced variables
// Checks whether condition returns a boolean
// operands of invalid type to operators

public class VerifyExpressions implements FormVisitor, QL.ast.ExpressionVisitor<Type>, TypeVisitor {

	private final Environment environment;

	public VerifyExpressions(Environment environment) {
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
    }

    @Override
    public void visit(ComputedQuestion question) {
        question.getComputedQuestion().accept(this);
    }

    @Override
    public void visit(Statement statement) {
        Type type = statement.getExpression().accept(this);
        check(new BooleanType(1), type); // TODO Type without a line number??
        statement.getBlock().accept(this); // TODO circulair dependencies?
    }

    @Override
    public void visit(IfElseStatement statement) {
        Type type = statement.getExpression().accept(this);
        check(new BooleanType(1), type);
        statement.getBlock().accept(this); // TODO circulair dependencies?
        statement.getElseBlock().accept(this); // TODO circulair dependencies?
    }


	@Override
	public Type visit(AddExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        // TODO look up type
        check(new IntegerType(1), type_lhs, type_rhs);

        return new IntegerType(expr.getLine());

	}

	@Override
	public Type visit(AndExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new BooleanType(1), type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(DivExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(EqExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(GEqExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(GExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

    @Override
    public Type visit(BooleanType type) {
        return new BooleanType(type.getLine());
    }

    @Override
    public Type visit(IntegerType type) {
        return new IntegerType(type.getLine());
    }

    @Override
    public Type visit(StringType type) {
        return new StringType(type.getLine());
    }

	@Override
	public Type visit(UnknownType type) {
		return new UnknownType(type.getLine());
	}

    @Override
	public Type visit(IdExpression id) {

        return environment.getType(id.getName(), id.getLine());
	}

	@Override
	public Type visit(LEqExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(LExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(MinusExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);

        check(new IntegerType(1), type_lhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(MulExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(NEqExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(NotExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);

        check(new BooleanType(1), type_lhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(OrExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new BooleanType(1), type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(PlusExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);

        check(new IntegerType(1), type_lhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(SubExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(BoolAtom expr) {
        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(IntegerAtom expr) {
        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(StringAtom expr) {
        return new StringType(expr.getLine());
	}

	private void check(Type expected, Type lhs, Type rhs) {

		check(expected, lhs);
		check(expected, rhs);
	}


    private void check(Type expected, Type current) {
    	// TODO is equal in Type or instance of?
        if (!expected.getKeyWord().equals(current.getKeyWord())) {
        	environment.getFaults().add(new Error("The type " + current.getKeyWord() + " is not of the expected type: "
    			+ expected.getKeyWord(), current.getLine()));
        }
    }


}
