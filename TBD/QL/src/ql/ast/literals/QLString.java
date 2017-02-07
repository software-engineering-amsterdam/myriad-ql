package ql.ast.literals;

import ql.ast.ASTNode;
import ql.ast.QLLiteral;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLString implements ASTNode, QLLiteral {
    private String qlString;

    public QLString(String qlString){
        this.qlString = qlString;
    }
}
