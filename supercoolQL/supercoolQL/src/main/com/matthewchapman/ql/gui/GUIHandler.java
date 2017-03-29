package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.environment.evaluation.ExpressionEvaluator;
import com.matthewchapman.ql.environment.values.BooleanValue;
import com.matthewchapman.ql.environment.values.Value;
import com.matthewchapman.ql.gui.widgets.QuestionWidget;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

/**
 * Created by matt on 15/03/2017.
 */
public class GUIHandler {

    private final FormEnvironment environment;
    private final ExpressionEvaluator evaluator;
    private final Stage stage;
    private final QuestionChangeObserver questionChangeObserver;

    public GUIHandler(Stage stage, FormEnvironment environment) {
        this.environment = environment;
        this.stage = stage;
        this.evaluator = new ExpressionEvaluator();
        this.questionChangeObserver = new QuestionChangeObserver(this);
        stage.setTitle(environment.getFormName());
        drawUI();
    }

    void onQuestionChange(String id, Value value) {
        this.environment.addOrUpdateValue(id, value);
        drawUI();
    }

    private void drawUI() {
        stage.setResizable(true);
        Scene scene = getScene();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @NotNull
    private Scene getScene() {
        VBox box = new VBox();

        QuestionWidgetFactory factory = new QuestionWidgetFactory();
        environment.evaluateExpressions(evaluator);

        for(Question question : environment.getQuestionsAsList()) {
            box.getChildren().add(getQuestionWidget(factory, question));
        }

        return new Scene(box);
    }

    private QuestionWidget getQuestionWidget(QuestionWidgetFactory factory, Question question) {
        String name = question.getName();
        Value value = environment.getValueByID(name);

        QuestionWidget widget = factory.getQuestionWidget(question, value, this.questionChangeObserver);

        if(environment.questionHasCondition(name)) {
            BooleanValue conditionValue = (BooleanValue) evaluator.evaluateExpression(name, environment.getConditionByID(name), environment.getValues());
            widget.setVisible(conditionValue.getValue());
        }

        if(environment.questionIsCalculated(name))
        {
            widget.setCalculated();
        }
        return widget;
    }

}
