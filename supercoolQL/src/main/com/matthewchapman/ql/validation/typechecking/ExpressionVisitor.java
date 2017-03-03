package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.expression.binary.Addition;
import com.matthewchapman.ql.ast.expression.binary.Subtraction;
import com.matthewchapman.ql.validation.AbstractQLVisitor;

/**
 * Created by matt on 03/03/2017.
 */
public class ExpressionVisitor extends AbstractQLVisitor<Void> {

    @Override
    public Void visit(Addition addition) {

        if(!addition.getLeft().isLeaf) {
            addition.getLeft().accept(this);
        } else if(!addition.getRight().isLeaf) {
            addition.getRight().accept(this);
        } else {
            System.out.println();
        }

        return null;
    }

    @Override
    public Void visit(Subtraction subtraction) {
        if(!subtraction.getLeft().isLeaf) {
            subtraction.getLeft().accept(this);
        } else if(!subtraction.getRight().isLeaf) {
            subtraction.getRight().accept(this);
        } else {
            System.out.println();
        }

        return null;
    }
}
