package ast;

import ast.type.BooleanType;
import ast.type.IntegerType;
import ast.type.StringType;
import ast.type.Type;
import ast.type.UnknownType;

public interface TypeVisitor {
    Type visit(BooleanType type);
    Type visit(IntegerType type);
    Type visit(StringType type);
	Type visit(UnknownType unknownType);

}
