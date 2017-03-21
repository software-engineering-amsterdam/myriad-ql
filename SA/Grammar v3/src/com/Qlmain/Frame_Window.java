package com.Qlmain;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.frame_Listeners.Checkbox_Listener;
import com.Qlmain.frame_Listeners.NumberField_Listener;
import com.Qlmain.frame_Listeners.StringField_Listener;
import com.Qlmain.QL.*;
import com.Qlmain.types_Of_Expr.Expression;
import com.Qlmain.types_Of_Expr.Number_ops.GiveValEqual;
import com.Qlmain.type_check.Type_Checking;
import com.Qlmain.types_Of_Expr.types.Type;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Frame_Window {

    //private static Map<String, Object> variablesAndValues;
    private static Map<IfStatement, JPanel> panelsAndConditions;
    private static Map<Question, JTextField> textFieldWithExprToEval;

    public void Custom_Frame(Form dataToDisplay, Map<String, Object> variablesAndValues) {
        //variablesAndValues = new HashMap<>();

        panelsAndConditions = new HashMap<>();
        textFieldWithExprToEval = new HashMap<>();
        JFrame frame = new JFrame("Check Box Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = new Dimension(500,500);
        frame.setPreferredSize(dim);
        frame.getContentPane().add( questionsToDisplay(dataToDisplay.getStatementList(), variablesAndValues));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel questionsToDisplay(List<Statement> dataToDisplay, Map<String, Object> variablesAndValues){
        JPanel newItemPanel = new JPanel();
        newItemPanel.setLayout(new BoxLayout(newItemPanel,BoxLayout.Y_AXIS));
        newItemPanel.setBackground(Color.WHITE);

        for (Statement statementItem : dataToDisplay) {

            if (statementItem instanceof Question) {

                Question questionItem = (Question) statementItem;
                JLabel jlabel = new JLabel(questionItem.text);
                jlabel.setForeground(Color.blue);
                Font font = new Font("Verdana", Font.ITALIC, 12);
                jlabel.setFont(font);

                newItemPanel = defineQuestionType(questionItem, newItemPanel, dataToDisplay, jlabel, variablesAndValues);

            }else if (statementItem instanceof IfStatement) {

                JPanel tempPan = questionsToDisplay( ((IfStatement) statementItem).getStatementsList(), variablesAndValues);
                panelsAndConditions.put((IfStatement) statementItem, tempPan );
                newItemPanel.add(tempPan);

                tempPan.setVisible(new Evaluation().setVisibleEval((IfStatement) statementItem) );

            }

        }
        return newItemPanel;
    }

    private JPanel defineQuestionType(Question questionItem, JPanel newItemPanel, List<Statement> dataToDisplay, JLabel jlabel, Map<String, Object> variablesAndValues) {
        Map<String,Type> variablesAndTypes = Type_Checking.getVariablesAndTypes();

        JPanel temppanel = new JPanel(new GridLayout(1,1));
        Dimension dim = new Dimension(500,30);
        temppanel.setMaximumSize(dim);
        temppanel.setBackground(Color.WHITE);

        temppanel.add(jlabel);

        if (variablesAndTypes.get(questionItem.name).check__bool_type() ) {

            //new Evaluation().variablesAndValuesStoreNewVal(questionItem.name, false);

            JCheckBox questionCheckBox = new JCheckBox();
            questionCheckBox.setBackground(Color.WHITE);
            questionCheckBox.addActionListener(new Checkbox_Listener(questionItem,dataToDisplay));
            temppanel.add(questionCheckBox);

        }else if (variablesAndTypes.get(questionItem.name).check__str_type() ){

            //new Evaluation().variablesAndValuesStoreNewVal(questionItem.name, 0);

            JTextField questionTextField = new JTextField();
            questionTextField.getDocument().addDocumentListener(new StringField_Listener(questionItem, questionTextField));
            questionTextField.setBackground(Color.WHITE);
            temppanel.add(questionTextField);

        }else if (variablesAndTypes.get(questionItem.name).check__int_type() ||
                variablesAndTypes.get(questionItem.name).check__mon_type()) {

            JTextField questionTextField = new JTextField();
            if (questionItem.type instanceof GiveValEqual) {
                questionTextField.setEditable(false);

                //Object evaluatedExpr = new Evaluation().evaluateIfExpression(questionItem.type);
                //new Evaluation().variablesAndValuesStoreNewVal(questionItem.name, evaluatedExpr);

                questionTextField.setText(variablesAndValues.get(questionItem.name).toString());
                textFieldWithExprToEval.put(questionItem, questionTextField);
            }else{
               // if (variablesAndTypes.get(questionItem.name).check__int_type()){
                //    new Evaluation().variablesAndValuesStoreNewVal(questionItem.name, 0);
               // }else{
                //    new Evaluation().variablesAndValuesStoreNewVal(questionItem.name, 0.0);
               // }
                questionTextField.getDocument().addDocumentListener(new NumberField_Listener(questionItem, questionTextField));
            }
            questionTextField.setBackground(Color.WHITE);

            temppanel.add(questionTextField);
        }
        newItemPanel.add(temppanel);
        return newItemPanel;
    }

    public void RedrawExpr() {

        Runnable doAssist = () -> {
            for (Question qu : textFieldWithExprToEval.keySet()) {
                Object reevaluated = new Evaluation().evaluateExpression(qu.type);
                textFieldWithExprToEval.get(qu).setText( reevaluated.toString() );
                new Evaluation().replaceValueInVariablesAndValues(qu.name, reevaluated);
            }
        };
        SwingUtilities.invokeLater(doAssist);
    }

    public void RedrawIf() {
        for (IfStatement statementItem : panelsAndConditions.keySet()) {

            panelsAndConditions.get(statementItem).setVisible(new Evaluation().setVisibleEval(statementItem));

        }

    }

    //public static Map<String, Object> getVariablesAndValues() { return variablesAndValues; }
    //public Map<IfStatement, JPanel> getPanelsAndConditions() {return panelsAndConditions;}
   // public static Map<Expression, JTextField> gettextFieldWithExprToEval() { return textFieldWithExprToEval; }
}
