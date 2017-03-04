package ql.ast;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 4-3-2017.
 */
public class Forms extends Array<Form> {

    public Forms(Form current, Array<Form> next) {
        super(current, next, 0);
    }

    public Forms(Form current) {
        super(current, 0);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
