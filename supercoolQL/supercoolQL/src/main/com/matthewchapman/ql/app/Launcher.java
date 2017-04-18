package com.matthewchapman.ql.app;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.environment.FormEnvironmentFactory;
import com.matthewchapman.ql.gui.GUIHandler;
import com.matthewchapman.ql.parsing.ASTBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by matt on 20/02/2017.
 * <p>
 * Main "Launcher" class for the application. Provides entry point & debug logic.
 */
public class Launcher extends Application {

    private static final Logger LOGGER = Logger.getLogger(Launcher.class.getName());

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

        if (fileContents != null) {
            ASTBuilder parser = new ASTBuilder();
            Form form = parser.buildQLAST(fileContents);

            if (form == null) {
                Platform.exit();
            } else if (parser.validateAST(form)) {
                LOGGER.info("AST Validated, handing off to UI");
                handOffToGUI(primaryStage, form);
            } else {
                LOGGER.severe("Errors found in validation, terminating");
            }
        } else {
            LOGGER.info("Closing: no file selected");
            Platform.exit();
        }
    }

    private void handOffToGUI(Stage stage, Form form) {
        new GUIHandler(stage, new FormEnvironmentFactory().getFormEnvironment(form));
    }

    @Nullable
    private String processInputFile(File file) {

        if (file == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Quitting");
            alert.setHeaderText("No file selected");
            alert.setContentText("Please select a valid QL file to continue.");
            alert.showAndWait();
            return null;
        } else {
            FileReader reader = new FileReader();
            return reader.readFile(file);
        }
    }

    private File getFileSelection(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open QL File");
        return fileChooser.showOpenDialog(primaryStage);
    }
}
