package org.ql.ast.type;

public class MoneyType extends FloatType {
    @Override
    public String toString() {
        return "money";
    }

    public boolean equals(Type type) {
        return this.toString().equals(type.toString());
    }
}
