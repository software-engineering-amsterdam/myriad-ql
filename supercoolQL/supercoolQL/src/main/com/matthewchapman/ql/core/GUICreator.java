package com.matthewchapman.ql.core;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.gui.FormWindow;
import com.matthewchapman.ql.validation.structure.QuestionCollection;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by matt on 15/03/2017.
 */
class GUICreator {

    void generateFormUI(Stage stage, Form ast) {

        QuestionCollection questionCollection = new QuestionCollection();
        questionCollection.gatherQuestions(ast);
        FormWindow window = new FormWindow().makeForm(questionCollection.getQuestionList());

        BorderPane pane = new BorderPane();
        pane.setCenter(window);

        Scene scene = new Scene(pane, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
