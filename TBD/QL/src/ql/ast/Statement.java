package ql.ast;

import ql.ast.visistor.interfaces.BaseVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public abstract class Statement extends ASTNode {

    public Statement(int rowNumber) {
        super(rowNumber);
    }

    public abstract  <T> T accept(BaseVisitor<T> visitor);
}
