package com.Qlmain;

import com.Qlmain.QL.Expr;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sotos on 14/3/2017.
 */
public class Evaluate_the_exprTest {
    @Test
    public void evaluateExp() throws Exception {

        Expr e = new Expr();
        Expr.TypeBoolean exl = e.new TypeBoolean(true);
        Expr e2 = new Expr();
        Expr.TypeBoolean exr = e2.new TypeBoolean(true);
        Expr e3 = new Expr();
        Expr.And ex = e3.new And(exl,exr);

    }

}