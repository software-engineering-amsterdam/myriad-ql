package org.lemonade.visitors;

import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public interface ASTVisitor<T> {

    T visit(Form form);

    T visit(Question question);

    T visit(Conditional conditional);

    T visit(Expression expression);

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

    T visit(BooleanValue booleanValue);

    T visit(DecimalValue decimalValue);

    T visit(MoneyValue moneyValue);

    T visit(IntegerValue integerValue);

    T visit(StringValue stringValue);

    T visit(IdentifierValue identifierValue);

    T visit(QLType qlType);
//    T visit(QLBooleanType qlBooleanType);
//    T visit(QLDateType qlDateType);
//    T visit(QLDecimalType qlDecimalType);
//    T visit(QLIntegerType qlIntegerType);
//    T visit(QLMoneyType qlMoneyType);
//    T visit(QLStringType qlStringType);
}
