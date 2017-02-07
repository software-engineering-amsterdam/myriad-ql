package ql.ast.type;

import ql.ast.ASTNode;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLInt implements ASTNode {
    private int qlInteger;

    public QLInt(int qlInteger){
        this.qlInteger = qlInteger;
    }
}
