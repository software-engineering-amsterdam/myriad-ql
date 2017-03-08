package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.binary.Addition;
import com.matthewchapman.ql.ast.expression.binary.Subtraction;
import com.matthewchapman.ql.validation.AbstractQLVisitor;

/**
 * Created by matt on 03/03/2017. 222
 *
 * Visitor to check expressions for validity (circular dependency, types, etc).
 */
public class ExpressionChecker extends AbstractQLVisitor<Type> {

    @Override
    public Type visit(Parameter parameter) {
        return super.visit(parameter);
    }

    @Override
    public Type visit(Addition addition) {
        return super.visit(addition);
    }

    @Override
    public Type visit(Subtraction subtraction) {
        return super.visit(subtraction);
    }
}
