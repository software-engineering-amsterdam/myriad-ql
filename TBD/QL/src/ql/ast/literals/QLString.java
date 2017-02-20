package ql.ast.literals;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLString extends QLLiteral {
    private final String qlString;

    public QLString(String qlString){
        this.qlString = qlString;
    }

    public String getValue() {
        return qlString;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QLString qlString1 = (QLString) o;

        return qlString != null ? qlString.equals(qlString1.qlString) : qlString1.qlString == null;
    }
}
