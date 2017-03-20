package ql.gui;

/**
 * Created by Erik on 20-3-2017.
 */
public class GUIEvaluator {
    private GUIForm form;

    public void setGUIForm(GUIForm form){
        this.form = form;
    }

    public void evaluate() {
        visit(form);
    }

    public void visit(GUIForm guiNode) {

    }

}
