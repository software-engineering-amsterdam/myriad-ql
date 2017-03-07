package org.uva.taxfree.gui;

import org.uva.taxfree.model.environment.Environment;
import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.declarations.NamedNode;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class QuestionForm {
    private final BlockNode mFormNode;

    public QuestionForm(BlockNode formNode) {
        mFormNode = formNode;
    }

    public QuestionForm(Environment environment) {
        mFormNode = environment.getRootNode();
    }

    public void show() {
        generateForm();
        mFormNode.setVisible(true);
    }

    public void printDeclarations() {
        mFormNode.printDeclarations();
    }

    private JFrame generateForm() {
        JFrame frame = new JFrame(mFormNode.toString());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createComponents());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(640, 480));
        frame.pack();
        return frame;
    }

    private JPanel createComponents() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setLayout(new BoxLayout(widgetPanel, BoxLayout.Y_AXIS));
        fillWidgetPanel(widgetPanel);
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    private void fillWidgetPanel(JPanel parentPanel) {
        for (NamedNode q : extractDeclarations()) {
            parentPanel.add(q.getWidget());
        }
    }

    private Set<NamedNode> extractDeclarations() {
        Set<NamedNode> questions = new LinkedHashSet<>();
        mFormNode.retrieveDeclarations(questions);
        return questions;
    }

    public void updateVisibility() {
        mFormNode.setVisible(true);
    }

    public void printValues() {
        mFormNode.printValue();
    }
}
