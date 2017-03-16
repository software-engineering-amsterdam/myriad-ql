package com.Qlmain.Frame_Listeners;

import com.Qlmain.Frame_Window;
import com.Qlmain.Types_Of_Expr.Expression;

import javax.swing.*;
import java.util.Map;


public class ReevaluateExpr {

    public ReevaluateExpr() {

        Runnable doAssist = () -> {
            Map<Expression, JTextField> textFieldWithExprToEval = Frame_Window.gettextFieldWithExprToEval();
            for (Expression exp : textFieldWithExprToEval.keySet()) {
                textFieldWithExprToEval.get(exp).setText( exp.exprEvaluateVisitor( ).toString() );
            }
        };
        SwingUtilities.invokeLater(doAssist);

    }
}
