package ql.ast;

import ql.ast.type.BooleanType;
import ql.ast.type.IntegerType;
import ql.ast.type.StringType;
import ql.ast.type.UnknownType;

public interface TypeVisitor<T> {

    T visit(BooleanType type);
    T visit(IntegerType type);
    T visit(StringType type);
	T visit(UnknownType unknownType);
}
