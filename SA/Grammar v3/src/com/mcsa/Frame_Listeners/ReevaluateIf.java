package com.mcsa.Frame_Listeners;

import com.mcsa.Frame_Window;
import com.mcsa.QL.IfStatement;

import javax.swing.*;
import java.util.Map;

/**
 * Created by sotos on 6/3/2017.
 */
public class ReevaluateIf {

    public ReevaluateIf() {
        Map<IfStatement, JPanel> panelsandconditions = Frame_Window.getPanelsAndConditions();
        for (IfStatement statementItem : panelsandconditions.keySet()) {

            if (!(boolean) Frame_Window.evaluateExp( statementItem.getIfCase())) {
                panelsandconditions.get(statementItem).setVisible(false);
            }else {
                panelsandconditions.get(statementItem).setVisible(true);
            }
        }
    }

}
