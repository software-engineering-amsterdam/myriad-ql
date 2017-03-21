package org.ql.ast.type;

public interface TypeCompatibility {
    boolean isCompatibleWith(TypeCompatibility type);

    default boolean isCompatibleWith(BooleanType type) {
        return false;
    }

    default boolean isCompatibleWith(DateType type) {
        return false;
    }

    default boolean isCompatibleWith(FloatType type) {
        return false;
    }

    default boolean isCompatibleWith(IntegerType type) {
        return false;
    }

    default boolean isCompatibleWith(MoneyType type) {
        return false;
    }

    default boolean isCompatibleWith(StringType type) {
        return false;
    }

    default boolean isCompatibleWith(UnknownType type) {
        return false;
    }
}
