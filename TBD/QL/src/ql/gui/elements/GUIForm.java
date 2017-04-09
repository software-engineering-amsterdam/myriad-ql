package ql.gui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ql.gui.evaluator.BaseEvaluator;
import ql.gui.evaluator.JSONEvaluator;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Erik on 28-2-2017.
 */
public class GUIForm extends GUIElement {
    private final GUIElement statements;
    private final JSONEvaluator jsonEvaluator = new JSONEvaluator();

    public GUIForm(String name, GUIElement statements) {
        this.statements = statements;
        this.setPadding(new Insets(10));
        this.setSpacing(8);

        Text textTitle = new Text(name);
        textTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Button button1 = new Button("Submit");
        button1.setOnAction(event -> {
            try {
                FileWriter fileWriter = new FileWriter("outPutFile.json");
                fileWriter.write(jsonEvaluator.toJson(this).toString());
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.getChildren().add(textTitle);
        this.getChildren().add(statements);
        this.getChildren().add(button1);
    }

    public GUIElement getStatements() {
        return statements;
    }

    public <T> T accept(BaseEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
