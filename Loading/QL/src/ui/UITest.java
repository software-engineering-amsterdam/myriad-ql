package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;

import ast.Form;
import ast.Question;
import ast.Visitor;

public class UITest extends Application {
	
	private static Form form;
	
    public static void main(Form f) {
    	form = f;
        launch();
    }
    
    
    // TODO hide implementation ArrayList
    List<TextField> renderQuestions(GridPane grid, Form form) {
        
    	QuestionnaireVisitor qVisitor =  new QuestionnaireVisitor(grid);
    	qVisitor.visit(form);
    	return qVisitor.getActiveQuestions();
    	
//    	int rowIndex = 0;
//        ArrayList<TextField> answers = new ArrayList<>();
//        for (Question question : form.getBlock().getQuestions()) {
//            Label questionLabel = new Label(question.getLabel());
//            grid.add(questionLabel, 0, 1 + rowIndex);
//                 
//            TextField userTextField = new TextField();
//            grid.add(userTextField, 1, 1 + rowIndex);
//            answers.add(userTextField);
//            ++rowIndex;
//        }
//        
//        return answers;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(form.getId());
        
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        Text scenetitle;
        scenetitle = new Text(form.getId());
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        List<TextField> answers = renderQuestions(grid, form);
 
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
            	actiontarget.setText(answers.get(0).getText());
            }
        });

        primaryStage.show();
    }
}
