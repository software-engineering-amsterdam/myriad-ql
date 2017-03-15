package com.matthewchapman.ql.core;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.parsing.AntlrErrorListener;
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

        if (file == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Quitting");
            alert.setHeaderText("No file selected");
            alert.setContentText("Please select a valid QL file to continue.");
            alert.showAndWait();
        } else {
            FileReader reader = new FileReader();
            String fileContents = reader.readFile(file);

            CoreParser parser = new CoreParser();
            Form form = parser.buildQLAST(fileContents);

            if(form == null) {
                Platform.exit();
            } else if(parser.validateAST(form)) {
                new GUICreator().generateFormUI(primaryStage, form);
            } else {
                Platform.exit();
            }
        }
    }

    private File getFileSelection(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open QL File");
        return fileChooser.showOpenDialog(primaryStage);
    }
}
