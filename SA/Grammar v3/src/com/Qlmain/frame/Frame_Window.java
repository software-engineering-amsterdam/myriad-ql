package com.Qlmain.frame;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.frame.frameListeners.CheckboxListener;
import com.Qlmain.frame.frameListeners.NumberFieldListener;
import com.Qlmain.frame.frameListeners.StringFieldListener;
import com.Qlmain.QL.*;
import com.Qlmain.typesOfExpr.numberOps.numericalExpressions.GiveValEqual;
import com.Qlmain.typeCheck.TypeChecking;
import com.Qlmain.typesOfExpr.types.Type;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Frame_Window {

    private Map<IfStatement, JPanel> panelsAndConditions;
    private Map<Question, JTextField> textFieldWithExprToEval;

    public void Custom_Frame(Form dataToDisplay, Map<String, Object> variablesAndValues, TypeChecking typeCheck, Evaluation evaluation) {

        panelsAndConditions = new HashMap<>();
        textFieldWithExprToEval = new HashMap<>();
        JFrame frame = new JFrame("Check Box Test");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Dimension dim = new Dimension(500,500);
        frame.setPreferredSize(dim);
        frame.getContentPane().add( questionsToDisplay(dataToDisplay.getStatementList(), variablesAndValues, typeCheck, evaluation));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel questionsToDisplay(List<Statement> dataToDisplay, Map<String, Object> variablesAndValues, TypeChecking typeCheck, Evaluation evaluation){
        JPanel newItemPanel = new JPanel();
        newItemPanel.setLayout(new BoxLayout(newItemPanel,BoxLayout.Y_AXIS));
        newItemPanel.setBackground(Color.WHITE);

        for (Statement statementItem : dataToDisplay) {

            if (statementItem.isQuestion()) {

                Question questionItem = (Question) statementItem;
                JLabel jlabel = new JLabel(questionItem.text);
                jlabel.setForeground(Color.blue);
                Font font = new Font("Verdana", Font.ITALIC, 12);
                jlabel.setFont(font);

                newItemPanel = defineQuestionType(questionItem, newItemPanel, jlabel, variablesAndValues, typeCheck, evaluation);

            }else if (statementItem.isIfStatement()) {

                JPanel tempPan = questionsToDisplay( ((IfStatement) statementItem).getStatementsList(), variablesAndValues, typeCheck, evaluation);
                panelsAndConditions.put((IfStatement) statementItem, tempPan );
                newItemPanel.add(tempPan);

                tempPan.setVisible(evaluation.setVisibleEval((IfStatement) statementItem) );

            }

        }
        return newItemPanel;
    }

    private JPanel defineQuestionType(Question questionItem, JPanel newItemPanel, JLabel jlabel, Map<String, Object> variablesAndValues, TypeChecking typeCheck, Evaluation evaluation) {
        Map<String,Type> variablesAndTypes = typeCheck.getVariablesAndTypes();

        JPanel tempPanel = new JPanel(new GridLayout(1,1));
        Dimension dim = new Dimension(500,30);
        tempPanel.setMaximumSize(dim);
        tempPanel.setBackground(Color.WHITE);

        tempPanel.add(jlabel);

        if (variablesAndTypes.get(questionItem.name).checkBoolType() ) {

            JCheckBox questionCheckBox = new JCheckBox();
            questionCheckBox.setBackground(Color.WHITE);
            questionCheckBox.addActionListener(new CheckboxListener(questionItem, evaluation, this));
            tempPanel.add(questionCheckBox);

        }else if (variablesAndTypes.get(questionItem.name).checkStrType() ){

            JTextField questionTextField = new JTextField();
            questionTextField.getDocument().addDocumentListener(new StringFieldListener(questionItem, questionTextField, evaluation));
            questionTextField.setBackground(Color.WHITE);
            tempPanel.add(questionTextField);

        }else if (variablesAndTypes.get(questionItem.name).checkIntType() ||
                variablesAndTypes.get(questionItem.name).checkMonType()) {

            JTextField questionTextField = new JTextField();
            if (questionItem.type instanceof GiveValEqual) {
                questionTextField.setEditable(false);

                questionTextField.setText(variablesAndValues.get(questionItem.name).toString());
                textFieldWithExprToEval.put(questionItem, questionTextField);
            }else{

                questionTextField.getDocument().addDocumentListener(new NumberFieldListener(questionItem, questionTextField, typeCheck, evaluation, this));
            }
            questionTextField.setBackground(Color.WHITE);

            tempPanel.add(questionTextField);
        }
        newItemPanel.add(tempPanel);
        return newItemPanel;
    }

    public void RedrawExpr(Evaluation evaluation) {

        Runnable doAssist = () -> {
            for (Question qu : textFieldWithExprToEval.keySet()) {
                Object reevaluated = evaluation.evaluateExpression(qu.type);
                textFieldWithExprToEval.get(qu).setText( reevaluated.toString() );
                evaluation.replaceValueInVariablesAndValues(qu.name, reevaluated);
            }
        };
        SwingUtilities.invokeLater(doAssist);
    }

    public void RedrawIf(Evaluation evaluation) {
        for (IfStatement statementItem : panelsAndConditions.keySet()) {

            panelsAndConditions.get(statementItem).setVisible(evaluation.setVisibleEval(statementItem));

        }

    }

}
