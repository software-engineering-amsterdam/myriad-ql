package QL.ast;

import QL.ast.type.BooleanType;
import QL.ast.type.IntegerType;
import QL.ast.type.StringType;
import QL.ast.type.Type;
import QL.ast.type.UnknownType;

public interface TypeVisitor {

    Type visit(BooleanType type);
    Type visit(IntegerType type);
    Type visit(StringType type);
	Type visit(UnknownType unknownType);
}
