package org.ql.ast.type;

public interface TypeVisitor<T, C> {
    T visitBooleanType(BooleanType booleanType, C context);
    T visitDateType(DateType dateType, C context);
    T visitFloatType(FloatType floatType, C context);
    T visitIntegerType(IntegerType integerType, C context);
    T visitMoneyType(MoneyType moneyType, C context);
    T visitStringType(StringType stringType, C context);
    T visitUnknownType(UnknownType unknownType, C context);
}
