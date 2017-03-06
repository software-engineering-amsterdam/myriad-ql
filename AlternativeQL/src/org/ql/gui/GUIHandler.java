package org.ql.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.ast.statement.Question;
import org.ql.collection.visitor.QuestionCollectVisitor;
import org.ql.evaluator.Evaluator;
import org.ql.evaluator.ValueTable;
import org.ql.gui.elements.QuestionElement;
import org.ql.gui.elements.QuestionElementBuilder;
import org.ql.parser.Parser;
import org.ql.typechecker.issues.IssuesStorage;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.TypeChecker;
import org.ql.typechecker.circular_dependencies.CircularDependenciesResolver;

import java.util.List;

// TODO: Only the GUI related stuff should be here (extract main start/load application from this class)
public class GUIHandler {

    private MainStage primaryStage;

    public GUIHandler(Stage primaryStage, Form form) {
        primaryStage.show();
        runGUI(form);
    }

    public void runGUI(Form form) {
        ValueTable valueTable = new ValueTable();
        GUIEval guiEval = new GUIEval(valueTable);
        QuestionElementBuilder questionElementBuilder = new QuestionElementBuilder(valueTable);
        createQuestionWidgets(form, questionElementBuilder);
    }

    public void createQuestionWidgets(Form form, QuestionElementBuilder questionElementBuilder) {
        QuestionCollectVisitor questionCollectVisitor = new QuestionCollectVisitor();
        List<Question> questions = questionCollectVisitor.collect(form);

        for (Question question : questions) {
            QuestionElement questionElement = questionElementBuilder.visitQuestion(question, null);
            System.out.println(questionElement.getQuestion().getQuestionText());
            System.out.println(questionElement.getQuestion().getType());
            System.out.println(questionElement.getValue().getPlainValue());
        }
    }
}
