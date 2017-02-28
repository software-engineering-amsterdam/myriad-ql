package ql.view.elements;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Erik on 28-2-2017.
 */
public class QLFormBox extends VBox {

    public QLFormBox(String name, QLStatementsBox statementsBox) {
        this.setPadding(new Insets(10));
        this.setSpacing(8);

        Text textTitle = new Text(name);
        textTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.getChildren().add(textTitle);
        this.getChildren().add(statementsBox);
    }
}
