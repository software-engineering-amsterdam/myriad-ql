package com.Qlmain.Frame_Listeners;

import com.Qlmain.Evaluate_the_expr;
import com.Qlmain.Frame_Window;
import com.Qlmain.QL.Expr;

import javax.swing.*;
import java.util.Map;

/**
 * Created by sotos on 6/3/2017.
 */
public class ReevaluateExpr {

    public ReevaluateExpr() {

        Runnable doAssist = () -> {
            Map<Expr, JTextField> textFieldWithExprToEval = Frame_Window.gettextFieldWithExprToEval();
            for (Expr exp : textFieldWithExprToEval.keySet()) {
                textFieldWithExprToEval.get(exp).setText(Evaluate_the_expr.evaluateExp( exp ).toString());
            }
        };
        SwingUtilities.invokeLater(doAssist);

    }
}
