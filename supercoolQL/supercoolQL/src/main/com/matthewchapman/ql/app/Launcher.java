package com.matthewchapman.ql.app;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.environment.ValueTableObserver;
import com.matthewchapman.ql.gui.GUIHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by matt on 20/02/2017.
 * <p>
 * Main "Launcher" class for the application. Provides entry point & debug logic.
 */
public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        File file = new File("res/test.txt");

        if (!this.getParameters().getUnnamed().contains("-debug")) {
            file = getFileSelection(primaryStage);
        }

        String fileContents = processInputFile(file);

        if(fileContents != null) {
            ASTBuilder parser = new ASTBuilder();
            Form form = parser.buildQLAST(fileContents);

            if (form == null) {
                Platform.exit();
            } else if (parser.validateAST(form)) {
                handOffToGUI(primaryStage, form);
            }
        }
    }

    private void handOffToGUI(Stage stage, Form form) {
        ValueTableObserver controller = new ValueTableObserver();
        FormEnvironment env = new FormEnvironment(form, controller);
        new GUIHandler(env, controller, stage);
    }

    private String processInputFile(File file) {
        String fileContents = "";
        if (file == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Quitting");
            alert.setHeaderText("No file selected");
            alert.setContentText("Please select a valid QL file to continue.");
            alert.showAndWait();
            Platform.exit();
        } else {
            FileReader reader = new FileReader();
            fileContents = reader.readFile(file);
        }
        return fileContents;
    }

    private File getFileSelection(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open QL File");
        return fileChooser.showOpenDialog(primaryStage);
    }
}
