package com.Qlmain;

import com.Qlmain.Frame_Listeners.Checkbox_Listener;
import com.Qlmain.Frame_Listeners.NumberField_Listener;
import com.Qlmain.Frame_Listeners.StringField_Listener;
import com.Qlmain.QL.*;
import com.Qlmain.type_check.Type_Checking;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sotos on 27/2/2017.
 */
public class Frame_Window {

    private static Map<String, Object> variablesAndValues;
    private static Map<IfStatement, JPanel> panelsAndConditions;
    private static Map<Expr, JTextField> textFieldWithExprToEval;

    public void Custom_Frame(Form dataToDisplay) {
        variablesAndValues = new HashMap<>();
        panelsAndConditions = new HashMap<>();
        textFieldWithExprToEval = new HashMap<>();
        JFrame frame = new JFrame("Check Box Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = new Dimension(500,500);
        frame.setPreferredSize(dim);

        frame.getContentPane().add( questionsToDisplay(dataToDisplay.getStatementList()));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static JPanel questionsToDisplay(List<Statement> dataToDisplay){
        JPanel newItemPanel = new JPanel();
        newItemPanel.setLayout(new BoxLayout(newItemPanel,BoxLayout.Y_AXIS));

        for (Statement statementItem : dataToDisplay) {
            if (statementItem instanceof Question) {

                Question questionItem = (Question) statementItem;
                JLabel jlabel = new JLabel(questionItem.text);
                jlabel.setForeground(Color.blue);
                Font font = new Font("Verdana", Font.ITALIC, 12);
                jlabel.setFont(font);

                newItemPanel = defineQuestionType(questionItem, newItemPanel, dataToDisplay, jlabel);

            }else if (statementItem instanceof IfStatement) {

                JPanel tempPan = questionsToDisplay( ((IfStatement) statementItem).getStatementsList());
                panelsAndConditions.put((IfStatement) statementItem, tempPan );
                newItemPanel.add(tempPan);

                if (!(boolean) Evaluate_the_expr.evaluateExp(((IfStatement) statementItem).getIfCase())) {
                    tempPan.setVisible(false);
                }else {
                    tempPan.setVisible(true);
                }
            }

        }
        return newItemPanel;
    }

    private static JPanel defineQuestionType(Question questionItem, JPanel newItemPanel, List<Statement> dataToDisplay, JLabel jlabel) {
        Map<String,Expr.Type> variablesAndTypes = Type_Checking.getVariablesAndTypes();
        JPanel temppanel = new JPanel(new GridLayout(1,1));

        temppanel.add(jlabel);
        temppanel.setBackground(Color.WHITE);
        if (variablesAndTypes.get(questionItem.name) == Expr.Type.BOOLEAN ) {

            variablesAndValues.put(questionItem.name, false);
            JCheckBox questionCheckBox = new JCheckBox();
            questionCheckBox.setBackground(Color.WHITE);
            questionCheckBox.addActionListener(new Checkbox_Listener(questionItem,dataToDisplay));
            temppanel.add(questionCheckBox);

        }else if (variablesAndTypes.get(questionItem.name) == Expr.Type.STRING ){

            variablesAndValues.put(questionItem.name, 0);
            JTextField questionTextField = new JTextField();
            questionTextField.getDocument().addDocumentListener(new StringField_Listener(questionItem, questionTextField));
            questionTextField.setBackground(Color.WHITE);
            temppanel.add(questionTextField);

        }else if (variablesAndTypes.get(questionItem.name) == Expr.Type.INTEGER ||
                variablesAndTypes.get(questionItem.name) == Expr.Type.MONEY) {

            if (variablesAndTypes.get(questionItem.name) == Expr.Type.INTEGER){
                variablesAndValues.put(questionItem.name, 0);
            }else{
                variablesAndValues.put(questionItem.name, 0.0);
            }

            JTextField questionTextField = new JTextField();
            if (questionItem.type instanceof Expr.giveValEqual) {
                questionTextField.setEditable(false);
                questionTextField.setText(Evaluate_the_expr.evaluateExp(questionItem.type).toString());
                textFieldWithExprToEval.put(questionItem.type, questionTextField);
            }else{
                questionTextField.getDocument().addDocumentListener(new NumberField_Listener(questionItem, questionTextField));
            }
            questionTextField.setBackground(Color.WHITE);

            temppanel.add(questionTextField);
        }
        newItemPanel.add(temppanel);
        return newItemPanel;
    }

    public static Map<String, Object> getVariablesAndValues() { return variablesAndValues; }
    public static Map<IfStatement, JPanel> getPanelsAndConditions() {
        return panelsAndConditions;
    }
    public static Map<Expr, JTextField> gettextFieldWithExprToEval() { return textFieldWithExprToEval; }
}
