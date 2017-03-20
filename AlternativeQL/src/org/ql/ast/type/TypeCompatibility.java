package org.ql.ast.type;

public interface TypeCompatibility {
    boolean isCompatibleWith(TypeCompatibility type);
}
