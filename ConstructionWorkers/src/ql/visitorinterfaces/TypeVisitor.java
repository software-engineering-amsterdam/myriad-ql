/**
 * TypeVisitor.java.
 */

package ql.visitorinterfaces;

import ql.astnodes.types.*;

public interface TypeVisitor<T> {
    T visit(BooleanType type);
    T visit(StringType type);
    T visit(IntegerType type);
    T visit(MoneyType type);
}
