package QL.semantic;


import QL.ast.Block;
import QL.ast.BlockItem;
import QL.ast.ComputedQuestion;
import QL.ast.Form;
import QL.ast.FormVisitor;
import QL.ast.IfElseStatement;
import QL.ast.Question;
import QL.ast.Statement;
import QL.ast.TypeVisitor;
import QL.ast.atom.BoolAtom;
import QL.ast.atom.IntegerAtom;
import QL.ast.atom.StringAtom;
import QL.ast.expression.AddExpr;
import QL.ast.expression.AndExpr;
import QL.ast.expression.DivExpr;
import QL.ast.expression.EqExpr;
import QL.ast.expression.GEqExpr;
import QL.ast.expression.GExpr;
import QL.ast.expression.IdExpr;
import QL.ast.expression.LEqExpr;
import QL.ast.expression.LExpr;
import QL.ast.expression.MinusExpr;
import QL.ast.expression.MulExpr;
import QL.ast.expression.NEqExpr;
import QL.ast.expression.NotExpr;
import QL.ast.expression.OrExpr;
import QL.ast.expression.PlusExpr;
import QL.ast.expression.SubExpr;
import QL.ast.type.BooleanType;
import QL.ast.type.IntegerType;
import QL.ast.type.StringType;
import QL.ast.type.Type;
import QL.ast.type.UnknownType;
import QL.errorhandling.Error;


/**
 * VerifyExpressions checks for
 * <li> unreferenced variables
 * <li> invalid type operations
 * <li> whether expressions return a boolean
 *
 */
public class VerifyExpressions implements FormVisitor, QL.ast.ExpressionVisitor<Type>, TypeVisitor<Type> {

	private final Environment environment;

	VerifyExpressions(Environment environment) {
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
	    Type type = question.getComputedQuestion().accept(this);
        check(question.getType(), type);
    }

    @Override
    public void visit(Statement statement) {
        Type type = statement.getExpression().accept(this);
        check(new BooleanType(statement.getLine()), type);
        statement.getBlock().accept(this);
    }

    @Override
    public void visit(IfElseStatement statement) {
        Type type = statement.getExpression().accept(this);
        check(new BooleanType(statement.getLine()), type);
        statement.getBlock().accept(this);
        statement.getElseBlock().accept(this);
    }

	@Override
	public Type visit(AddExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(expr.getLine()), type_lhs, type_rhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(AndExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new BooleanType(expr.getLine()), type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(DivExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(expr.getLine()), type_lhs, type_rhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(EqExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(GEqExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(GExpr expr) {
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
	public Type visit(IdExpr id) {
    	
    	if (!environment.getReferenceTable().variableExists(id.getName())) {
	        environment.getFaults().add(new Error("The variable: " + id.getName() + 
	        		" is not defined", id.getLine()));
	        return new UnknownType(id.getLine());
    	}
    	
        return environment.getReferenceTable().getType(id.getName());
	}

	@Override
	public Type visit(LEqExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(LExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(MinusExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);

        check(new IntegerType(expr.getLine()), type_lhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(MulExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(expr.getLine()), type_lhs, type_rhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(NEqExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(NotExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);

        check(new BooleanType(expr.getLine()), type_lhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(OrExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new BooleanType(expr.getLine()), type_lhs, type_rhs);

        return new BooleanType(expr.getLine());
	}

	@Override
	public Type visit(PlusExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);

        check(new IntegerType(expr.getLine()), type_lhs);

        return new IntegerType(expr.getLine());
	}

	@Override
	public Type visit(SubExpr expr) {
        Type type_lhs = expr.getLhs().accept(this);
        Type type_rhs = expr.getRhs().accept(this);

        check(new IntegerType(expr.getLine()), type_lhs, type_rhs);

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
    	if (!expected.equals(current)) {
        	environment.getFaults().add(new Error("The type " + current.getKeyWord() 
        	+ " is not of the expected type: "
    		+ expected.getKeyWord(), current.getLine()));
        }
    }


}
