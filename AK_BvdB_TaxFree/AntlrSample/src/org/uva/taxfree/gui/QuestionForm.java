package org.uva.taxfree.gui;

import org.uva.taxfree.model.NamedNode;
import org.uva.taxfree.model.Node;

import javax.swing.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class QuestionForm {
    private Node mFormNode;

    public QuestionForm(Node formNode) {
        mFormNode = formNode;
    }

    public void show() {
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

    private void fillWidgetPanel(JPanel parentPanel) {
        for (Node q : extractQuestions()) {
            parentPanel.add(((NamedNode) q).getWidget());
        }
    }

    private Set<NamedNode> extractQuestions() {
        Set<NamedNode> questions = new LinkedHashSet<>();
        mFormNode.retrieveQuestions(questions);
        return questions;
    }

}
