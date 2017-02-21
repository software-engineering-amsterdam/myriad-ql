package org.uva.taxfree.gui;

import org.uva.taxfree.model.NamedNode;
import org.uva.taxfree.model.Node;

import javax.swing.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class QuestionForm {
    private Node mFormNode;
    private JFrame mFrame;
    public QuestionForm(Node formNode) {
        mFormNode = formNode;
    }

    public void show() {
        generateForm();
        mFormNode.setVisibility(true);
    }

    public void printData(){
        mFormNode.printData();
    }

    private void generateForm() {
        mFrame = new JFrame(mFormNode.toString());
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.add(createComponents());
        mFrame.pack();
        mFrame.setLocationRelativeTo(null);
        mFrame.setVisible(true);
    }

    private JPanel createComponents() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setLayout(new BoxLayout(widgetPanel, BoxLayout.Y_AXIS));
        fillWidgetPanel(widgetPanel);
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    private void fillWidgetPanel(JPanel parentPanel) {
        for (NamedNode q : extractQuestions()) {
            parentPanel.add(q.getWidget());
        }
    }

    private Set<NamedNode> extractQuestions() {
        Set<NamedNode> questions = new LinkedHashSet<>();
        mFormNode.retrieveQuestions(questions);
        return questions;
    }

    public void updateVisibility() {
        mFormNode.setVisibility(true);
    }

    public void printAll() {
        mFormNode.printAll();

    }
}
