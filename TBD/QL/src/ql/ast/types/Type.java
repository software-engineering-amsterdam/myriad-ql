package ql.ast.types;


import ql.ast.expressions.ArithmeticOp;
import ql.ast.expressions.EqualityOp;
import ql.ast.expressions.LogicalOp;
import ql.ast.expressions.RelationalOp;
import ql.visistor.interfaces.ExpressionVisitor;
import ql.visistor.interfaces.TypeVisitor;

/**
 * Created by Erik on 21-2-2017.
 */
public abstract class Type {

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass();
    }

    public Type checkTypes(ArithmeticOp op, Type other) {
        return new ErrorType();
    }

    protected Type checkTypesEval(ArithmeticOp op, IntType other)  {
        return new ErrorType();
    }

    protected Type checkTypesEval(ArithmeticOp op, FloatType other)  {
        return new ErrorType();
    }


    public Type checkTypes(ArithmeticOp op) {
        return new ErrorType();
    }



    public Type checkTypes(LogicalOp op, Type other) {
        return new ErrorType();
    }

    protected Type checkTypesEval(LogicalOp op, BooleanType other)  {
        return new ErrorType();
    }


    public Type checkTypes(LogicalOp op) {
        return new ErrorType();
    }




    public Type checkTypes(RelationalOp op, Type other) {
        return new ErrorType();
    }

    protected Type checkTypesEval(RelationalOp op, IntType other)  {
        return new ErrorType();
    }

    protected Type checkTypesEval(RelationalOp op, FloatType other)  {
        return new ErrorType();
    }


    public Type checkTypes(EqualityOp op, Type other) {
        return new ErrorType();
    }

    protected Type checkTypesEval(EqualityOp op, BooleanType other)  {
        return new ErrorType();
    }

    protected Type checkTypesEval(EqualityOp op, StringType other)  {
        return new ErrorType();
    }

    protected Type checkTypesEval(EqualityOp op, FloatType other)  {
        return new ErrorType();
    }

    protected Type checkTypesEval(EqualityOp op, IntType other)  {
        return new ErrorType();
    }


    public abstract <T> T accept(TypeVisitor<T> visitor);
}
