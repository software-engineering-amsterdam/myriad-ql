package org.ql.gui;

import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.evaluator.ValueTable;
import org.ql.gui.elements.QuestionElement;
import org.ql.gui.elements.visitor.QuestionValueVisitor;
import org.ql.gui.elements.visitor.BranchVisitor;

import java.util.List;

// TODO: Only the GUI related stuff should be here (extract main start/load application from this class)
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
        ValueTable valueTable = new ValueTable();

        while (valueTable.size() == 0 || valueTable.hasUnknownValues()) {
            ValueTable currentVT = valueTable.copy();
            questionValueVisitor.visitForm(form, valueTable);
            if (currentVT.equals(valueTable) || (valueTable.size()+currentVT.size()) == 0) {
                break;
            }
        }

        List<QuestionElement> visibleElements = branchVisitor.visitForm(form, valueTable);
        System.out.println(visibleElements.size());
    }

}
