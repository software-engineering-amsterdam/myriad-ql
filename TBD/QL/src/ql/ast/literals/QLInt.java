package ql.ast.literals;

import ql.ast.ASTNode;
import ql.ast.QLLiteral;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLInt  implements ASTNode, QLLiteral {
    private int qlInteger;

    public QLInt(int qlInteger){
        this.qlInteger = qlInteger;
    }
}
