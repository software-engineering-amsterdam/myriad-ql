package com.mcsa;

import com.mcsa.Frame_Listeners.Checkbox_Listener;
import com.mcsa.Frame_Listeners.TextField_Listener;
import com.mcsa.QL.*;
import com.mcsa.type_check.Type_Checking;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sotos on 27/2/2017.
 */
public class Frame_Window {

    private static Map<String, Object> variablesAndValues = new HashMap<>();
    private static Map<IfStatement, JPanel> panelsAndConditions = new HashMap<>();

    public void Custom_Frame(Form dataToDisplay) {
        JFrame frame = new JFrame("Check Box Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add( questionsToDisplay(dataToDisplay.getStatementList()));
        frame.add(mainPanel);

        //frame.addComponentListener(new Component_Listener());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static JPanel questionsToDisplay(List<Statement> dataToDisplay){
        JPanel newItemPanel = new JPanel();
        newItemPanel.setBackground( Color.WHITE );
        newItemPanel.setLayout(new GridLayout(Type_Checking.getVariablesAndTypes().size(),1));
        for (Statement statementItem : dataToDisplay) {
            if (statementItem instanceof Question) {
                Question questionItem = (Question) statementItem;
                JLabel jlabel = new JLabel(questionItem.text);
                jlabel.setForeground(Color.blue);
                Font font = new Font("Verdana", Font.ITALIC, 12);
                jlabel.setFont(font);
                //Border margin = new EmptyBorder(15,10,15,20);
                //jlabel.setBorder(margin);
                newItemPanel.add(jlabel);

                Map<String,Object> variablesAndTypes = Type_Checking.getVariablesAndTypes();

                if (variablesAndTypes.get(questionItem.name).equals("boolean") ) {
                    variablesAndValues.put(questionItem.name, false);
                    JCheckBox questionCheckBox = new JCheckBox();
                    questionCheckBox.setBackground(Color.WHITE);
                    questionCheckBox.addActionListener(new Checkbox_Listener(questionItem,dataToDisplay));
                    newItemPanel.add(questionCheckBox);
                } else if (variablesAndTypes.get(questionItem.name).equals("integer") ||
                        variablesAndTypes.get(questionItem.name).equals("money")) {
                    variablesAndValues.put(questionItem.name, 0);
                    JTextField questionTextField = new JTextField();
                    //questionTextField.setPreferredSize( new Dimension( 10, 10 ) );
                    questionTextField.setBackground(Color.WHITE);
                    questionTextField.getDocument().addDocumentListener(new TextField_Listener(questionItem, questionTextField));
                    newItemPanel.add(questionTextField);
                }
            }else if (statementItem instanceof IfStatement) {
                JPanel tempPan = questionsToDisplay( ((IfStatement) statementItem).getStatementsList());
                panelsAndConditions.put((IfStatement) statementItem, tempPan );
                newItemPanel.add(tempPan);

                if (!(boolean) evaluateExp(((IfStatement) statementItem).getIfCase())) {
                    tempPan.setVisible(false);
                }else {
                    tempPan.setVisible(true);
                }
            }

        }
        //mainPanel.add(newItemPanel);
        return newItemPanel;
    }

    public static Map<IfStatement, JPanel> getPanelsAndConditions() {
        return panelsAndConditions;
    }

    public static Object evaluateExp (Expr ifexp) {

        if ( ifexp instanceof Expr.And) {
            Expr.And t = (Expr.And) ifexp;
            Object evl = evaluateExp(t.getAndlhs());
            Object evr = evaluateExp(t.getAndrhs());
            System.out.println("evaluate " + evl + " " + evr);
            return ((boolean) evl && (boolean) evr);
        }else if (ifexp instanceof Expr.Or) {
            Expr.Or t = (Expr.Or) ifexp;
            Object evl = evaluateExp(t.getOrlhs());
            Object evr = evaluateExp(t.getOrrhs());
            return ( (boolean) evl || (boolean) evr );
        }else if (ifexp instanceof Expr.Mul) {
            Expr.Mul t = (Expr.Mul) ifexp;
            Object evl = evaluateExp(t.getMullhs());
            Object evr = evaluateExp(t.getMulrhs());
            return (int) evl * (int) evr;
        }else if (ifexp instanceof Expr.Div) {
            Expr.Div t = (Expr.Div) ifexp;
            Object evl = evaluateExp(t.getDivlhs());
            Object evr = evaluateExp(t.getDivrhs());
            return (int) evl / (int) evr;
        }else if (ifexp instanceof Expr.Add) {
            Expr.Add t = (Expr.Add) ifexp;
            Object evl = evaluateExp(t.getAddlhs());
            Object evr = evaluateExp(t.getAddrhs());
            return (int) evl + (int) evr;
        }else if (ifexp instanceof Expr.Sub) {
            Expr.Sub t = (Expr.Sub) ifexp;
            Object evl = evaluateExp(t.getSublhs());
            Object evr = evaluateExp(t.getSubrhs());
            return (int) evl - (int) evr;
        }else if (ifexp instanceof Expr.Smaller) {
            Expr.Smaller t = (Expr.Smaller) ifexp;
            Object evl = evaluateExp(t.getSmallerlhs());
            Object evr = evaluateExp(t.getSmallerrhs());
            return (int) evl < (int) evr;
        }else if (ifexp instanceof Expr.Greater) {
            Expr.Greater t = (Expr.Greater) ifexp;
            Object evl = evaluateExp(t.getGreaterlhs());
            Object evr = evaluateExp(t.getGreaterrhs());
            return (int) evl > (int) evr;
        }else if (ifexp instanceof Expr.SmallerEq) {
            Expr.SmallerEq t = (Expr.SmallerEq) ifexp;
            Object evl = evaluateExp(t.getSmallerEqlhs());
            Object evr = evaluateExp(t.getSmallerEqrhs());
            return (int) evl <= (int) evr;
        }else if (ifexp instanceof Expr.GreaterEq) {
            Expr.GreaterEq t = (Expr.GreaterEq) ifexp;
            Object evl = evaluateExp(t.getGreaterEqlhs());
            Object evr = evaluateExp(t.getGreaterEqrhs());
            return (int) evl >= (int) evr;
        }else if (ifexp instanceof Expr.Equal) {
            Expr.Equal t = (Expr.Equal) ifexp;
            Object evl = evaluateExp(t.getEquallhs());
            Object evr = evaluateExp(t.getEqualrhs());
            return (int) evl == (int) evr;
        }else if (ifexp instanceof Expr.giveValEqual) {
            Expr.giveValEqual t = (Expr.giveValEqual) ifexp;
            Object evr = evaluateExp(t.getValEqualrhs());
            System.out.println(evr + " " + t.getValEquallhs());
            return evr;
        }else if (ifexp instanceof Expr.StrValue) {
            Expr.StrValue t = (Expr.StrValue) ifexp;
            return t.getStrValue();
        }
        else if (ifexp instanceof Expr.IdValue) {
            Expr.IdValue t = (Expr.IdValue) ifexp;
            if (variablesAndValues.containsKey(t.getIdValue())) {
                return variablesAndValues.get(t.getIdValue());
            }else {
                return false;
            }
        }
        else if (ifexp instanceof Expr.IntValue) {
            Expr.IntValue t = (Expr.IntValue) ifexp;
            return t.getIntValue();
        }

        return true;
    }

    public static Map<String, Object> getVariablesAndValues() {
        return variablesAndValues;
    }
}
