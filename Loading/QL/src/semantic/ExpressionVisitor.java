package semantic;


import ast.*;
import ast.atom.BoolAtom;
import ast.atom.IntegerAtom;
import ast.atom.StringAtom;
import ast.expression.*;
import ast.type.BooleanType;
import ast.type.IntegerType;
import ast.type.StringType;
import ast.type.Type;

// Checks unreferenced variables
// Checks whether condition returns a boolean
// operands of invalid type to operators
// TODO rename
public class ExpressionVisitor implements FormVisitor, ast.ExpressionVisitor<Type>, TypeVisitor {
	
	private final Environment environment;
	
	public ExpressionVisitor(Environment environment) {
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

    // TODO computed question
    @Override
    public void visit(ComputedQuestion question) {
        question.getComputedQuestion().accept(this);
    }

    @Override
    public void visit(Statement statement) {
        Type type = statement.getExpression().accept(this);
        check(new BooleanType(1), type);
        statement.getBlock().accept(this); // TODO circulair dependencies?
    }


	@Override
	public Type visit(AddExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

        return new IntegerType(expr.getLine());

	}

	@Override
	public Type visit(AndExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

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

        check(new IntegerType(1), type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(GEqExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(GExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

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
	public Type visit(IdExpression id) {
        if (!environment.variableExists(id.getName())) {
            throw new RuntimeException("The variable with name " + id.getName() +
                    " on line "+ id.getLine() + " is not defined");
        }

        return environment.getType(id.getName());
	}

	@Override
	public Type visit(LEqExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(LExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

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

        check(new IntegerType(1), type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(NotExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);

        check(new IntegerType(1), type_lhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(OrExpression expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(1), type_lhs, type_rhs);

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

	// TODO do we want to add the throw after this function
	private void check(Type expected, Type lhs, Type rhs) {
	    System.out.println(expected);
	    System.out.println(lhs);
	    System.out.println(rhs);

	    if (!expected.getType().equals(lhs.getType())) {
	        throw new RuntimeException("The type on line " + lhs.getLine() + " is not the expected type " + expected);
        }

        if (!expected.getType().equals(rhs.getType())) {
            throw new RuntimeException("The type on line " + rhs.getLine() + " is not the expected type " + expected);
        }
	}

    private void check(Type expected, Type lhs) {
        if (!expected.getType().equals(lhs.getType())) {
            throw new RuntimeException("The type on line " + lhs.getLine() + " is not the expected type " + expected);
        }
    }
}
