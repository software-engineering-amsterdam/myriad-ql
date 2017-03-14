package QL.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import QL.Environment;
import QL.Faults;
import QL.ast.Form;
import QL.value.Value;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Questionnaire extends Application implements Notifier {
	
	// TODO do not make static
	private static Form form;
	private static Environment environment;
	private static GridPane grid;
	private static Stage pStage; // TODO
	private static Faults faults;
	
    public void main(Form f, Environment env, Faults flts) {
    	form = f;
    	environment = env;
    	faults = flts;
 
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) {

        pStage = primaryStage;
        
    	// TODO improve with http://www-acad.sheridanc.on.ca/~jollymor/prog24178/javafx7.html
    	faults.check();

        primaryStage.setTitle(form.getId());
        
        grid = initGrid();
        
        Scene scene = new Scene(grid, 500, 275);
        primaryStage.setScene(scene);
        
        renderQuestionnaire(grid);
        
        primaryStage.show();

    }
    
    private GridPane initGrid() {
    	
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        return grid;
    }
    
    
    private void renderQuestionnaire(GridPane grid) {
    	
        renderTitle(grid, form.getId());
        
        List<Row> activeQuestions = createQuestions();
        
    	renderQuestions(activeQuestions);
    	
        Button btn = renderButton(grid, activeQuestions.size() + 2);

        // TODO move to function submit
        Text actiontarget = new Text();
        grid.add(actiontarget, 1, activeQuestions.size() + 2);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	for (Row activeQuestion : activeQuestions) {
            		
            		Value answer = activeQuestion.getAnswer();
            		if (!answer.isSet()) {
                        actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Please Fill in all Fields");
                        return;
            		}
            	}  
                actiontarget.setFill(Color.SPRINGGREEN);
                actiontarget.setText("Thank you for filling\n in the questionnaire");

                exportQuestionnaire(activeQuestions);
            }
        });
    }
    
    private void renderTitle(GridPane grid, String title) {
        Text scenetitle;
        scenetitle = new Text(title);
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
    }
    
    private List<Row> createQuestions() {
        
    	QEvaluator qVisitor = new QEvaluator(environment, this);
    	qVisitor.visit(form);
    	return qVisitor.getActiveQuestions();
    	
    }
    
    private void renderQuestions(List<Row> activeQuestions) {

    	int rowIndex = 1;
        for (Row question : activeQuestions) {
            
        	Label questionLabel = new Label(question.getLabel());
            grid.add(questionLabel, 0, rowIndex);
            grid.add(question.getControl(), 1, rowIndex);
            
            ++rowIndex;
        }
        
    }
    
    private Button renderButton(GridPane grid, int rowIndex) {
    	 
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 3, rowIndex);
        
        return btn;
    }
    
	public void updateQuestionnaire(String name, Value newValue) {
    	Value oldAnswer = environment.getAnswer(name);
		if (!environment.isAnswered(name) || !(oldAnswer.equals(newValue))) {

			environment.addAnswer(name, newValue);
	    	grid.getChildren().clear();
	        renderQuestionnaire(grid);
	        grid.lookup("#" + name).requestFocus();
		}
	}

	private void exportQuestionnaire(List<Row> activeQuestions) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(pStage);
        
        if (file == null) {
        	return;
        }

        try {
            JsonObjectBuilder questionnaire = Json.createObjectBuilder();

            for (Row question : activeQuestions) {
                questionnaire.add(question.getName(), question.getAnswer().convertToString());
            }

            OutputStream os = new FileOutputStream(file);
            JsonWriter jsonWriter = Json.createWriter(os);
            jsonWriter.writeObject(questionnaire.build());
            jsonWriter.close();
        } 
        catch (IOException ex) {
            throw new RuntimeException(
                    "This should never happen, I know this file exists", ex);
        }
	}

}
