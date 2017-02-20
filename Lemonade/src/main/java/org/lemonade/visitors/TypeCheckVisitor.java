package org.lemonade.visitors;

import org.lemonade.nodes.Block;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Type;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;

/**
 *
 */
public class TypeCheckVisitor implements TypeCheckInterface {

    @Override
    public Block visit(Block block) {
        System.err.println("block hit");
        return block;
    }

    @Override
    public Boolean visit(Form form) {
        for (Block block : form.getBlocks()) {
            block.accept(this);
        }
        return true;
    }

    @Override
    public Block visit(Question question) {

        System.err.println("question hit");
        return null;
    }

    @Override
    public Block visit(Conditional conditional) {

        System.err.println("conditional hit");
        return null;
    }

    @Override
    public Type visit(Expression expression) {
        return null;
    }

    @Override
    public Type visit(AndBinary andBinary) {
        return null;
    }

    @Override
    public Type visit(OrBinary orBinary) {
        return null;
    }

    @Override
    public Type visit(PlusBinary plusBinary) {
        return null;
    }

    @Override
    public Type visit(ProductBinary productBinary) {
        return null;
    }

    @Override
    public Type visit(MinusBinary minusBinary) {
        return null;
    }

    @Override
    public Type visit(DivideBinary divideBinary) {
        return null;
    }

    @Override
    public Type visit(EqBinary eqBinary) {
        return null;
    }

    @Override
    public Type visit(NEqBinary nEqBinary) {
        return null;
    }

    @Override
    public Type visit(GTBinary gtBinary) {
        return null;
    }

    @Override
    public Type visit(GTEBinary gteBinary) {
        return null;
    }

    @Override
    public Type visit(LTBinary ltBinary) {
        return null;
    }

    @Override
    public Type visit(LTEBinary lteBinary) {
        return null;
    }

    @Override
    public Type visit(BangUnary bangUnary) {
        return null;
    }

    @Override
    public Type visit(NegUnary negUnary) {
        return null;
    }

    @Override
    public Type visit(BooleanLit booleanLit) {
        return null;
    }

    @Override
    public Type visit(DecimalLit decimalLit) {
        return null;
    }

    @Override
    public Type visit(IdentifierLit identifierLit) {
        return null;
    }

    @Override
    public Type visit(IntegerLit integerLit) {
        return null;
    }

    @Override
    public Type visit(StringLit stringLit) {
        return null;
    }
}
