package ASTnodes.visitors;

import ASTnodes.types.*;

/**
 * Created by LGGX on 15-Feb-17.
 */
public interface TypeVisitor<T> {

    T visit(BooleanType type);
    T visit(StringType type);
    T visit(IntegerType type);
    T visit(MoneyType type);

}
