package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.Expression;

/**
 * Created by matt on 24/02/2017.
 */
abstract class UnaryOperation extends Expression {

    public int getLine() {
        return super.getLine();
    }

    public int getColumn() {
        return super.getColumn();
    }

}
