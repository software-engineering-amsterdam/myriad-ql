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
        drawUI();
    }

    void onQuestionChange(String id, Value value) {
        this.environment.addOrUpdateValue(id, value);
        drawUI();
    }

    private void drawUI() {
        VBox box = new VBox();

        QuestionWidgetFactory factory = new QuestionWidgetFactory();
        environment.evaluateExpressions(evaluator);

        for(Question question : environment.getQuestionsAsList()) {
            box.getChildren().add(getQuestionWidget(factory, question));
        }

        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }

    private QuestionWidget getQuestionWidget(QuestionWidgetFactory factory, Question question) {
        String name = question.getName();
        Value value = environment.getValueByName(name);

        QuestionWidget widget = factory.getQuestionWidget(question, value, this.questionChangeObserver);

        if(environment.questionHasCondition(question.getName())) {
            BooleanValue conditionValue = (BooleanValue) evaluator.evaluateExpression(name, environment.getConditionByName(question.getName()), environment.getValues());
            widget.setVisible(conditionValue.getValue());
        }

        if(environment.questionIsCalculated(name))
        {
            widget.setEditable(false);
        }
        return widget;
    }

}
