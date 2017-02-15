package org.uva.taxfree.GUI;

import org.uva.taxfree.model.Node;
import org.uva.taxfree.model.NamedNode;

import javax.swing.*;
import java.util.ArrayList;

public class QuestionForm {
    private Node mFormNode;

    public QuestionForm(Node formNode) {
        mFormNode = formNode;
    }

    public void show(){
        generateForm();
    }

    private void generateForm() {
        JFrame frame = new JFrame(mFormNode.getId());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createComponents());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createComponents() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setLayout(new BoxLayout(widgetPanel, BoxLayout.Y_AXIS));
        fillWidgetPanel(widgetPanel);
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    private void fillWidgetPanel(JPanel parentPanel){
       for (NamedNode q : extractQuestions()){
            parentPanel.add(q.getWidget());
        }
    }

    private ArrayList<NamedNode> extractQuestions() {
        ArrayList<NamedNode> questions = new ArrayList<NamedNode>();

        mFormNode.retrieveQuestions(questions);
        return questions;
    }

}
