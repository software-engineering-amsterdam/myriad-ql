package com.Qlmain;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.frame_Listeners.Checkbox_Listener;
import com.Qlmain.frame_Listeners.NumberField_Listener;
import com.Qlmain.frame_Listeners.StringField_Listener;
import com.Qlmain.QL.*;
import com.Qlmain.types_Of_Expr.Number_ops.GiveValEqual;
import com.Qlmain.type_check.Type_Checking;
import com.Qlmain.types_Of_Expr.types.Type;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Frame_Window {

    private static Map<IfStatement, JPanel> panelsAndConditions;
    private static Map<Question, JTextField> textFieldWithExprToEval;

    public void Custom_Frame(Form dataToDisplay, Map<String, Object> variablesAndValues) {

        panelsAndConditions = new HashMap<>();
        textFieldWithExprToEval = new HashMap<>();
        JFrame frame = new JFrame("Check Box Test");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
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

                newItemPanel = defineQuestionType(questionItem, newItemPanel, jlabel, variablesAndValues);

            }else if (statementItem instanceof IfStatement) {

                JPanel tempPan = questionsToDisplay( ((IfStatement) statementItem).getStatementsList(), variablesAndValues);
                panelsAndConditions.put((IfStatement) statementItem, tempPan );
                newItemPanel.add(tempPan);

                tempPan.setVisible(new Evaluation().setVisibleEval((IfStatement) statementItem) );

            }

        }
        return newItemPanel;
    }

    private JPanel defineQuestionType(Question questionItem, JPanel newItemPanel, JLabel jlabel, Map<String, Object> variablesAndValues) {
        Map<String,Type> variablesAndTypes = Type_Checking.getVariablesAndTypes();

        JPanel tempPanel = new JPanel(new GridLayout(1,1));
        Dimension dim = new Dimension(500,30);
        tempPanel.setMaximumSize(dim);
        tempPanel.setBackground(Color.WHITE);

        tempPanel.add(jlabel);

        if (variablesAndTypes.get(questionItem.name).check__bool_type() ) {

            JCheckBox questionCheckBox = new JCheckBox();
            questionCheckBox.setBackground(Color.WHITE);
            questionCheckBox.addActionListener(new Checkbox_Listener(questionItem));
            tempPanel.add(questionCheckBox);

        }else if (variablesAndTypes.get(questionItem.name).check__str_type() ){

            JTextField questionTextField = new JTextField();
            questionTextField.getDocument().addDocumentListener(new StringField_Listener(questionItem, questionTextField));
            questionTextField.setBackground(Color.WHITE);
            tempPanel.add(questionTextField);

        }else if (variablesAndTypes.get(questionItem.name).check__int_type() ||
                variablesAndTypes.get(questionItem.name).check__mon_type()) {

            JTextField questionTextField = new JTextField();
            if (questionItem.type instanceof GiveValEqual) {
                questionTextField.setEditable(false);

                questionTextField.setText(variablesAndValues.get(questionItem.name).toString());
                textFieldWithExprToEval.put(questionItem, questionTextField);
            }else{

                questionTextField.getDocument().addDocumentListener(new NumberField_Listener(questionItem, questionTextField));
            }
            questionTextField.setBackground(Color.WHITE);

            tempPanel.add(questionTextField);
        }
        newItemPanel.add(tempPanel);
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

}
