package com.Qlmain.Frame_Listeners;

import com.Qlmain.Frame_Window;
import com.Qlmain.QL.IfStatement;

import javax.swing.*;
import java.util.Map;

public class ReevaluateIf {

    public ReevaluateIf() {
        Map<IfStatement, JPanel> panelsandconditions = Frame_Window.getPanelsAndConditions();
        for (IfStatement statementItem : panelsandconditions.keySet()) {

            if (!(boolean) statementItem.getIfCase().exprEvaluateVisitor() ) {
                panelsandconditions.get(statementItem).setVisible(false);
            }else {
                panelsandconditions.get(statementItem).setVisible(true);
            }
        }

    }

}
