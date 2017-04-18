/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/visitorinterfaces/TypeVisitor.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.visitorinterfaces;

import ql.astnodes.types.*;

public interface TypeVisitor<T> {
    T visit(BooleanType type);
    T visit(StringType type);
    T visit(IntegerType type);
    T visit(MoneyType type);
}
