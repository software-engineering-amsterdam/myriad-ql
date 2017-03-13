package org.uva.taxfree.gui;

import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.declarations.DeclarationNode;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;


public class QuestionForm {
    private final BlockNode mFormNode;

    public QuestionForm(BlockNode formNode) {
        mFormNode = formNode;
    }

    public void show() {
        generateForm();
        new FormRenderer(this);
        mFormNode.setVisible(true);
    }

    private void generateForm() {
        JFrame frame = new JFrame(mFormNode.toString());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createComponents());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(640, 480));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public JPanel createComponents(){
        throw new RuntimeException("Unimplemented");
    }

    private void fillWidgetPanel(JPanel parentPanel) {
        for (DeclarationNode q : extractDeclarations()) {
            parentPanel.add(q.getWidget());
        }
    }


    private Set<DeclarationNode> extractDeclarations() {
        Set<DeclarationNode> questions = new HashSet<>();
//        mFormNode.retrieveDeclarations(questions);
        return questions;
    }

    public void updateVisibility() {
        mFormNode.setVisible(true);
    }

}
