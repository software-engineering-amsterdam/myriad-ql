package org.lemonade.visitors.interfaces;

import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;

/**
 *
 */
public interface ExpressionVisitor<T> {

    T visit(AndBinary andBinary);

    T visit(OrBinary orBinary);

    T visit(PlusBinary plusBinary);

    T visit(ProductBinary productBinary);

    T visit(MinusBinary minusBinary);

    T visit(DivideBinary divideBinary);

    T visit(EqBinary eqBinary);

    T visit(NEqBinary nEqBinary);

    T visit(GTBinary gtBinary);

    T visit(GTEBinary gteBinary);

    T visit(LTBinary ltBinary);

    T visit(LTEBinary lteBinary);

    T visit(BangUnary bangUnary);

    T visit(NegUnary negUnary);

    T visit(BooleanLiteral booleanValue);

    T visit(DecimalLiteral decimalValue);

    T visit(DateLiteral dateValue);

    T visit(MoneyLiteral moneyValue);

    T visit(IntegerLiteral integerValue);

    T visit(StringLiteral stringValue);

    T visit(IdentifierLiteral identifierValue);
}
