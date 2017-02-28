package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class StringValue extends Value<String> implements Comparable<StringValue>{

    public StringValue(QLType type, String value) {
        super(type, value);
        assert type instanceof QLStringType;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StringValue)){
            return false;
        }
        StringValue that = (StringValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(StringValue that) {
        return this.getValue().compareTo(that.getValue());
    }
}
