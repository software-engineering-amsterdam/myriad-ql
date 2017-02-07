package ql.ast.type;

import ql.ast.ASTNode;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLString implements ASTNode {
    private String qlString;

    public QLString(String qlString){
        this.qlString = qlString;
    }
}
