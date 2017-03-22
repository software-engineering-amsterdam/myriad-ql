package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.environment.evaluation.ExpressionEvaluator;
import javafx.stage.Stage;

/**
 * Created by matt on 15/03/2017.
 */
public class GUIHandler {

    private final FormEnvironment environment;
    private final ExpressionEvaluator evaluator;
    private final Stage stage;

    public GUIHandler(Stage stage, FormEnvironment environment) {
        this.environment = environment;
        this.stage = stage;
        this.evaluator = new ExpressionEvaluator();
    }

}
