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
import value.StringValue;
import value.Value;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import ast.Form;
import ast.Question;
import ast.Visitor;

public class Questionnaire extends Application {
	
	// TODO staic variables??
	private static Form form;
	private static Map<String, Value> answers;
	
    public static void main(Form f) {
    	form = f;
    	answers = new HashMap<>();
        launch();
    }
        
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(form.getId());
        
        GridPane grid = initGrid();
        
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        
        renderTitle(grid, form.getId());
        
        renderQuestionnaire(primaryStage, grid);

    }
    
    private GridPane initGrid() {
    	
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        return grid;
    }
    
    private void renderQuestionnaire(Stage primaryStage, GridPane grid) {
        
    	Map<String, TextField> activeQuestions = renderQuestions(grid);
        
        Button btn = renderButton(grid, activeQuestions.size() + 1);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
                
        //Map<String, String> answers = new HashMap<>();
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	for (Map.Entry<String, TextField> activeQuestion : activeQuestions.entrySet()) {
            		Value answer = new StringValue(activeQuestion.getValue().getText());
            		answers.put(activeQuestion.getKey(), answer);
            	}          
            	renderQuestionnaire(primaryStage, grid);
            }
        });

        primaryStage.show();
    }
    
    private void renderTitle(GridPane grid, String title) {
        Text scenetitle;
        scenetitle = new Text(title);
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
    }
    
    private Map<String, TextField> renderQuestions(GridPane grid) {
        
    	QuestionnaireVisitor qVisitor =  new QuestionnaireVisitor(answers);
    	qVisitor.visit(form);

    	int rowIndex = 0;
        Map<String, TextField> answers = new HashMap<>();
        for (String question : qVisitor.getActiveQuestions()) {
            Label questionLabel = new Label(question);
            grid.add(questionLabel, 0, 1 + rowIndex);
                 
            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 1 + rowIndex);
            answers.put(question, userTextField);
            ++rowIndex;
        }
        return answers;
    }
    
    private Button renderButton(GridPane grid, int rowIndex) {
    	 
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, rowIndex);
        
        return btn;
    }
}
