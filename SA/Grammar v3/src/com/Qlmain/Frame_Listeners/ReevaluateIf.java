package com.Qlmain.Frame_Listeners;

import com.Qlmain.Evaluate_the_expr;
import com.Qlmain.Frame_Window;
import com.Qlmain.QL.IfStatement;

import javax.swing.*;
import java.util.Map;

/**
 * Created by sotos on 6/3/2017.
 */
public class ReevaluateIf {

    public ReevaluateIf() {
        Map<IfStatement, JPanel> panelsandconditions = Frame_Window.getPanelsAndConditions();
        for (IfStatement statementItem : panelsandconditions.keySet()) {

            if (!(boolean) Evaluate_the_expr.evaluateExp( statementItem.getIfCase())) {
                panelsandconditions.get(statementItem).setVisible(false);
            }else {
                panelsandconditions.get(statementItem).setVisible(true);
            }
        }

    }

}
