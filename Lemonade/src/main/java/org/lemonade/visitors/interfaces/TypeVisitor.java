package org.lemonade.visitors.interfaces;

import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLDateType;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.nodes.types.QLStringType;

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
