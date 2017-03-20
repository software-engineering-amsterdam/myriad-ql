package org.lemonade.gui;

import java.io.File;
import java.util.List;

import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.GuiLabelElement;
import org.lemonade.visitors.EvaluateVisitor;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class QLGui {

    // Gui wide elements
    private Stage stage;
    private File file;

    // File selection elements
    private Scene selectionScene;
    private VBox errorBox;
    private Button submitFileButton;

    // Questionnaire elements
    private Scene questionnaireScene;
    private Button submitQuestionnaireButton;
    private GridPane questionsGridPane;
    private int questionsRowCount;

    public QLGui(Stage stage) {
        this.stage = stage;
        setUpFileSelectionScene();
        setUpQuestionsPane();
    }

    public File getFile() {
        return file;
    }

    public Scene getSelectionScene() {
        return selectionScene;
    }

    public Button getSubmitFileButton() {
        return submitFileButton;
    }

    public Button getSubmitQuestionnaireButton() {
        return submitQuestionnaireButton;
    }

    private void setUpFileSelectionScene() {
        stage.setTitle("Form");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add((new FileChooser.ExtensionFilter("Select a .ql file", "*.ql")));

        Label fileLabel = new Label();

        final Button openButton = new Button("Select a questionnaire");
        openButton.setOnAction(e -> {
            file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                fileLabel.setText(file.getPath());
            }
        });

        submitFileButton = new Button("Submit");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(openButton, submitFileButton, fileLabel);

        errorBox = new VBox();
        errorBox.setSpacing(6);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(errorBox);
        scrollPane.setPrefViewportHeight(650);
        scrollPane.setPrefViewportWidth(950);

        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setBottomAnchor(hBox, 10.0);
        AnchorPane.setLeftAnchor(hBox, 5.0);
        AnchorPane.setTopAnchor(scrollPane, 10.0);
        AnchorPane.setLeftAnchor(scrollPane, 5.0);
        anchorPane.getChildren().addAll(hBox, scrollPane);
        anchorPane.setPadding(new Insets(10, 10, 10, 10));

        selectionScene = new Scene(anchorPane);
    }

    public void setUpQuestionnaireScene() {
        submitQuestionnaireButton = new Button("Submit form");

        final Button backButton = new Button("Select new questionnaire");
        backButton.setOnAction(e -> stage.setScene(selectionScene));

        final AnchorPane rootGroup = new AnchorPane();

        AnchorPane.setBottomAnchor(submitQuestionnaireButton, 10.0);
        AnchorPane.setLeftAnchor(submitQuestionnaireButton, 5.0);
        AnchorPane.setBottomAnchor(backButton, 10.0);
        AnchorPane.setRightAnchor(backButton, 5.0);
        AnchorPane.setTopAnchor(questionsGridPane, 10.0);
        AnchorPane.setLeftAnchor(questionsGridPane, 5.0);
        AnchorPane.setRightAnchor(questionsGridPane, 5.0);

        rootGroup.getChildren().addAll(questionsGridPane, backButton, submitQuestionnaireButton);
        rootGroup.setPadding(new Insets(10, 10, 10, 10));

        questionnaireScene = new Scene(rootGroup);
    }

    public void setUpQuestionsPane() {
        questionsGridPane = new GridPane();
        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setPercentWidth(100);
        questionsGridPane.getColumnConstraints().addAll(constraints);

        this.questionsRowCount = 0;
    }

    public void addErrors(String message, List<String> errors) {
        errorBox.getChildren().clear();
        errorBox.getChildren().add(new Label(message));

        for (String item : errors) {
            Label label = new Label(item);
            label.setTextFill(Color.RED);
            errorBox.getChildren().add(label);
        }
    }

    public void addQuestion(GuiLabelElement labelElement, GuiElement element) {
        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(3, 6, 3, 6));

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);

        GridPane.setConstraints(labelElement.getWidget(), 0, 0);
        GridPane.setConstraints(element.getWidget(), 1, 0);
        gridPane.getChildren().addAll(labelElement.getWidget(), element.getWidget());
        gridPane.managedProperty().bind(gridPane.visibleProperty());

        // Increment rowCount to place question on next row
        questionsGridPane.addRow(questionsRowCount++, gridPane);
    }

    public void addUserInputListeners(GuiForm guiRoot, EvaluateVisitor evaluateVisitor) {
        Parent rootGroup = questionnaireScene.getRoot();
        rootGroup.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> guiRoot.accept(evaluateVisitor));
        rootGroup.addEventFilter(KeyEvent.KEY_RELEASED, e -> guiRoot.accept(evaluateVisitor));
    }

    public void goToQuestionnaire() {
        this.stage.setScene(questionnaireScene);
    }
}
