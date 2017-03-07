package org.ql.gui;

import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.evaluator.ValueTable;
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
        ValueTable valueTable = questionValueVisitor.makeValueTable(form);
        List<QuestionElement> visibleElements = branchVisitor.visitForm(form, questionValueVisitor.makeValueTable(form));

        for(QuestionElement questionElement : visibleElements) {
            questionElement.getWidget().setValue(valueTable.lookup(questionElement.getQuestion().getId()));

            mainstage.addPaneToRootPane(questionElement.getWidget().getGridPane());
        }

    }

}
