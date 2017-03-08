package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.binary.Addition;
import com.matthewchapman.ql.ast.expression.binary.Subtraction;
import com.matthewchapman.ql.validation.AbstractQLVisitor;

/**
 * Created by matt on 03/03/2017. 222
 *
 * Visitor to check expressions for validity (circular dependency, types, etc).
 */
public class ExpressionChecker extends AbstractQLVisitor<Void> {

    @Override
    public Void visit(Parameter parameter) {
        return super.visit(parameter);
    }

    @Override
    public Void visit(Addition addition) {
        return super.visit(addition);
    }

    @Override
    public Void visit(Subtraction subtraction) {
        return super.visit(subtraction);
    }
}
