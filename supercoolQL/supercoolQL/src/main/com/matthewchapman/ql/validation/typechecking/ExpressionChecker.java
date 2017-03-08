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
        System.out.println("parameter: " + parameter.getID());
        return null;
    }

    @Override
    public Void visit(Addition addition) {
        System.out.println("addition");
        addition.getLeft().accept(this);
        addition.getRight().accept(this);
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction) {
        System.out.println("subtraction");
        subtraction.getLeft().accept(this);
        subtraction.getRight().accept(this);
        return null;
    }
}
