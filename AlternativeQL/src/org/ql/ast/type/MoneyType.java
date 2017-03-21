package org.ql.ast.type;

public class MoneyType extends FloatType {
    @Override
    public String toString() {
        return "money";
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visitMoneyType(this, context);
    }
}
