package QL.evaluation;

import QL.ast.atom.BoolAtom;
import QL.ast.atom.IntegerAtom;
import QL.ast.atom.StringAtom;
import QL.ast.expression.*;
import QL.evaluation.Evaluator;
import QL.value.Value;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class QEvaluatorTest extends Evaluator {

    public QEvaluatorTest() {
        super(null);
    }

    @Test
    public void evaluateAdd() throws IOException {
        Expression expr = new AddExpr(new IntegerAtom(5,1), new IntegerAtom(1,1), 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "6");
    }

    @Test
    public void evaluateAnd() throws IOException {
        Expression expr = new AndExpr(new BoolAtom(true,1), new BoolAtom(false,1), 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "false");
    }

    @Test
    public void evaluateDiv() throws IOException {
        Expression expr = new DivExpr(new IntegerAtom(8,1), new IntegerAtom(2,1), 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "4");

        expr = new DivExpr(new IntegerAtom(8,1), new IntegerAtom(0,1), 1);
        v = expr.accept(this);
        assertEquals(v.convertToString(), "0");
    }

    @Test
    public void evaluateEq() throws IOException {
        Expression expr = new EqExpr(new IntegerAtom(8,1), new IntegerAtom(2,1), 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "false");

        expr = new EqExpr(new BoolAtom(true,1), new BoolAtom(true,1), 1);
        v = expr.accept(this);
        assertEquals(v.convertToString(), "true");

        expr = new EqExpr(new StringAtom("test",1), new StringAtom("test",1), 1);
        v = expr.accept(this);
        assertEquals(v.convertToString(), "true");
    }

    @Test
    public void evaluateGq() throws IOException {
        Expression expr = new GEqExpr(new IntegerAtom(8,1), new IntegerAtom(2,1), 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "true");
    }

    @Test
    public void evaluateGr() throws IOException {
        Expression expr = new GExpr(new IntegerAtom(8,1), new IntegerAtom(2,1), 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "true");
    }

    @Test
    public void evaluateLq() throws IOException {
        Expression expr = new LEqExpr(new IntegerAtom(8,1), new IntegerAtom(2,1), 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "false");
    }

    @Test
    public void evaluateLe() throws IOException {
        Expression expr = new LExpr(new IntegerAtom(8,1), new IntegerAtom(2,1), 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "false");
    }

    @Test
    public void evaluateMinus() throws IOException {
        Expression expr = new MinusExpr(new IntegerAtom(5,1),1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "-5");
    }

    @Test
    public void evaluateMul() throws IOException {
        Expression expr = new MulExpr(new IntegerAtom(5,1), new IntegerAtom(5,1),1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "25");
    }

    @Test
    public void evaluateNeq() throws IOException {
        Expression expr = new NEqExpr(new IntegerAtom(8,1), new IntegerAtom(2,1), 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "true");

        expr = new NEqExpr(new BoolAtom(true,1), new BoolAtom(true,1), 1);
        v = expr.accept(this);
        assertEquals(v.convertToString(), "false");

        expr = new NEqExpr(new StringAtom("test",1), new StringAtom("test",1), 1);
        v = expr.accept(this);
        assertEquals(v.convertToString(), "false");
    }

    @Test
    public void evaluateNot() throws IOException {
        Expression expr = new NotExpr(new BoolAtom(true,1),1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "false");

        Expression lhs = new AndExpr(new BoolAtom(true,1),new BoolAtom(false,1),1);
        Expression rhs = new OrExpr(new BoolAtom(false,1),new BoolAtom(true,1),1);
        expr = new NotExpr(new AndExpr(lhs, rhs,1),1);
        v = expr.accept(this);
        assertEquals(v.convertToString(), "true");
    }

    @Test
    public void evaluateOr() throws IOException {
        Expression expr = new OrExpr(new BoolAtom(true,1),new BoolAtom(true,1),1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "true");

        expr = new OrExpr(new BoolAtom(true,1),new BoolAtom(false,1),1);
        v = expr.accept(this);
        assertEquals(v.convertToString(), "true");

        expr = new OrExpr(new BoolAtom(false,1),new BoolAtom(false,1),1);
        v = expr.accept(this);
        assertEquals(v.convertToString(), "false");
    }

    @Test
    public void evaluatePlus() throws IOException {
        Expression expr = new PlusExpr(new IntegerAtom(1,1),1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "1");

        expr = new PlusExpr(new IntegerAtom(-10,1),1);
        v = expr.accept(this);
        assertEquals(v.convertToString(), "-10");
    }

    @Test
    public void evaluateSub() throws IOException {
        Expression expr = new SubExpr(new IntegerAtom(1,1),new IntegerAtom(1,1),1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "0");
    }

    @Test
    public void evaluate() throws IOException {
        Expression lhs = new AddExpr(new IntegerAtom(5,1),new IntegerAtom(4,1),1);
        Expression rhs = new MulExpr(new IntegerAtom(3,1),new IntegerAtom(3,1),1);

        Expression expr = new EqExpr(lhs, rhs, 1);
        Value v = expr.accept(this);
        assertEquals(v.convertToString(), "true");

    }


}