package QL.ast;

import QL.ast.type.BooleanType;
import QL.ast.type.IntegerType;
import QL.ast.type.StringType;
import QL.ast.type.UnknownType;

public interface TypeVisitor<T> {

    T visit(BooleanType type);
    T visit(IntegerType type);
    T visit(StringType type);
	T visit(UnknownType unknownType);
}
