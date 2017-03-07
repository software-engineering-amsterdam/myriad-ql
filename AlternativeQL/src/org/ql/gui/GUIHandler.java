package org.ql.gui;

import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.evaluator.ValueTable;
import org.ql.gui.elements.QuestionElement;
import org.ql.gui.elements.visitor.QuestionValueVisitor;
import org.ql.gui.elements.visitor.VisibleQuestionsVisitor;

import java.util.List;

// TODO: Only the GUI related stuff should be here (extract main start/load application from this class)
public class GUIHandler {

    private final MainStage mainstage;
    private final VisibleQuestionsVisitor visibleQuestionsVisitor;
    private final QuestionValueVisitor questionValueVisitor;

    public GUIHandler(Stage primaryStage) {
        this.mainstage = new MainStage(primaryStage);

        QuestionElementContainer questionElementContainer = new QuestionElementContainer();

        visibleQuestionsVisitor = new VisibleQuestionsVisitor(questionElementContainer);
        questionValueVisitor = new QuestionValueVisitor(questionElementContainer);

        primaryStage.show();
    }

    public void runGUI(Form form) {
        ValueTable valueTable = new ValueTable();
        while (valueTable.hasUnknownValues()) {
            questionValueVisitor.visitForm(form, valueTable);
        }

        List<QuestionElement> visibleElements = visibleQuestionsVisitor.visitForm(form, valueTable);
    }
}
