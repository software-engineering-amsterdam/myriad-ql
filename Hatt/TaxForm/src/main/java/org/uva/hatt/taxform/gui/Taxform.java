package org.uva.hatt.taxform.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.ASTGenerator;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.uva.hatt.taxform.ast.nodes.items.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Taxform extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setHgap(15);
        root.setVgap(8);
        root.setPadding(new Insets(20));

        Form form;
        List<Item> questions = new ArrayList<>();
        String qlForm = "form taxOfficeExample { \"q1?\" val1: boolean \"q2?\" val2: string }";
        try {
            form = ASTGenerator.getForm(qlForm);
            questions = form.getQuestions();
        }catch (IOException e){};

        int rowcounter = 0;
        for (Item question: questions) {
            root.add(new Text(question.toString()), 0, ++rowcounter, 2, 1);
        }

        Scene scene = new Scene(root, 400, 200);

        stage.setTitle("Tax form");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}