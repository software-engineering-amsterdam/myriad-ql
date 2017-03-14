package org.lemonade;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static org.assertj.core.api.Assertions.assertThat;

public class GuiTest {

    public static class AsNonApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            // noop
        }
    }

    @BeforeClass
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(AsNonApp.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    public void emptyTextField() {
        final TextField textField = new TextField();
        assertThat(textField.getText()).isEqualTo("");
    }
}