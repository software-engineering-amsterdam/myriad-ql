package org.uva.taxfree.GUI;

import org.uva.taxfree.model.BoolQuestion;
import org.uva.taxfree.model.Node;
import org.uva.taxfree.model.QuestionNode;
import org.uva.taxfree.model.StringQuestion;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionForm {
    List<QuestionNode> questions;

    void generateForm(Node formNode) {
        JFrame frame = new JFrame(formNode.getId());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createComponents(formNode));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createComponents(Node formNode) {
        questions = extractQuestions(formNode);
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        generateQuestionPanel(checkBoxPanel);
        checkBoxPanel.setVisible(true);
        return checkBoxPanel;
    }

    private List extractQuestions(Node formNode) {
        List widgets = new ArrayList<QuestionNode>();
        widgets.add(new BoolQuestion("I am string", "theString", formNode));
        widgets.add(new StringQuestion("I am boolean", "theBoolean", formNode));
        return widgets;
    }

    void generateQuestionPanel(JPanel parent) {
        for (QuestionNode q : questions) {
            parent.add(q.getWidget());
        }
    }
}
