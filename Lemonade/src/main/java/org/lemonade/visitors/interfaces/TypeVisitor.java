package org.lemonade.visitors.interfaces;

import org.lemonade.nodes.types.*;

/**
 *
 */
public interface TypeVisitor<T> {

    T visit(QLIntegerType qlIntegerType);

    T visit(QLBooleanType qlBooleanType);

    T visit(QLDateType qlDateType);

    T visit(QLDecimalType qlDecimalType);

    T visit(QLMoneyType qlMoneyType);

    T visit(QLStringType qlStringType);
}
