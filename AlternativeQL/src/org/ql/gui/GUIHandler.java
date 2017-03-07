package org.ql.gui;

import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.gui.elements.QuestionElement;
import org.ql.gui.elements.visitor.QuestionValueVisitor;
import org.ql.gui.elements.visitor.BranchVisitor;

import java.util.List;

public class GUIHandler {

    private final MainStage mainstage;
    private final BranchVisitor branchVisitor;
    private final QuestionValueVisitor questionValueVisitor;

    public GUIHandler(Stage primaryStage) {
        this.mainstage = new MainStage(primaryStage);

        QuestionElementContainer questionElementContainer = new QuestionElementContainer();

        branchVisitor = new BranchVisitor(questionElementContainer);
        questionValueVisitor = new QuestionValueVisitor(questionElementContainer);

        primaryStage.show();
    }

    public void runGUI(Form form) {
        List<QuestionElement> visibleElements = branchVisitor.visitForm(form, questionValueVisitor.makeValueTable(form));

        System.out.println(visibleElements.size());
        for(QuestionElement questionElement : visibleElements) {
            System.out.println(questionElement.getQuestion().getQuestionLabel());
        }

    }

}
