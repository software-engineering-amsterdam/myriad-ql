package ql.ast.types;


import ql.ast.expressions.ArithmeticOp;
import ql.ast.expressions.EqualityOp;
import ql.ast.expressions.LogicalOp;
import ql.ast.expressions.RelationalOp;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Erik on 21-2-2017.
 */
public class Type {

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass();
    }

    public Type checkTypes(ArithmeticOp op, Type other) {
        throw new NotImplementedException();
    }

    protected Type checkTypesEval(ArithmeticOp op, IntType other)  {
        throw new NotImplementedException();
    }

    protected Type checkTypesEval(ArithmeticOp op, FloatType other)  {
        throw new NotImplementedException();
    }


    public Type checkTypes(ArithmeticOp op) {
        throw new NotImplementedException();
    }



    public Type checkTypes(LogicalOp op, Type other) {
        throw new NotImplementedException();
    }

    protected Type checkTypesEval(LogicalOp op, BooleanType other)  {
        throw new NotImplementedException();
    }


    public Type checkTypes(LogicalOp op) {
        throw new NotImplementedException();
    }




    public Type checkTypes(RelationalOp op, Type other) {
        throw new NotImplementedException();
    }

    protected Type checkTypesEval(RelationalOp op, IntType other)  {
        throw new NotImplementedException();
    }

    protected Type checkTypesEval(RelationalOp op, FloatType other)  {
        throw new NotImplementedException();
    }


    public Type checkTypes(EqualityOp op, Type other) {
        throw new NotImplementedException();
    }

    protected Type checkTypesEval(EqualityOp op, BooleanType other)  {
        throw new NotImplementedException();
    }

    protected Type checkTypesEval(EqualityOp op, StringType other)  {
        throw new NotImplementedException();
    }

    protected Type checkTypesEval(EqualityOp op, IntType other)  {
        throw new NotImplementedException();
    }

}
