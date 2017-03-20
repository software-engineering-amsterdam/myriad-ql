package QL.ui;

import QL.ReferenceTable;
import QL.ast.Form;
import QL.message.Message;
import QL.ui.message.MessageDialog;
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

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Questionnaire extends Application implements Notifier {
	
	// TODO do not make static
	private static Form form;
	private static Environment environment;
	private static GridPane grid;
	private static List<Message> messages;
	
    public void main(Form f, ReferenceTable variables, List<Message> msgs) {
    	form = f;
    	environment = new Environment(variables);
    	messages = msgs;
 
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) {


        showMessages();
        if (hasFatalMessage()) {
            return;
        }

        primaryStage.setTitle(form.getId());

        initGrid();
        Scene scene = new Scene(grid, 500, 275);
        primaryStage.setScene(scene);
        
        renderQuestionnaire();
        
        primaryStage.show();

    }

    private void showMessages() {
        if (messages.isEmpty()) {
            return;
        }

        new MessageDialog(messages);
    }

    private boolean hasFatalMessage() {
        boolean isFatalMessage = false;
        for (Message msg : messages) {
            isFatalMessage = isFatalMessage || msg.isFatal();
        }

        return isFatalMessage;
    }

    private void initGrid() {
    	
        grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    private void renderQuestionnaire() {
    	
        renderTitle(form.getId());
        
        List<Row> activeQuestions = createQuestions();
        
    	renderQuestions(activeQuestions);
    	
        Button btn = renderButton(activeQuestions.size() + 2);
              
        submit(btn, activeQuestions);
    }
    
    private void submit(Button btn, List<Row> activeQuestions) {
    	
        Text actiontarget = new Text();
        grid.add(actiontarget, 1, activeQuestions.size() + 2);
    	
        btn.setOnAction(e -> complete(activeQuestions, actiontarget));
    }
    
    private void complete(List<Row> activeQuestions, Text actiontarget) {
        
    	actiontarget.setFill(Color.GREEN);
        actiontarget.setText("Thank you for filling in the questionnaire");

        export(activeQuestions);
    	
    }
    
    private void renderTitle(String title) {
        Text scenetitle = new Text(title);
        
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
    
    private Button renderButton(int rowIndex) {
    	 
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 3, rowIndex);
        
        return btn;
    }
    
	public void updateQuestionnaire(String name, Value newValue) {

		if (!environment.isAnswered(name) || !(environment.getAnswer(name).equals(newValue))) {

			environment.addAnswer(name, newValue);
	    	grid.getChildren().clear();
	        renderQuestionnaire();
	        grid.lookup("#" + name).requestFocus();
		}
	}

	private void export(List<Row> activeQuestions) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        Stage qStage = new Stage();

        File file = fileChooser.showSaveDialog(qStage);
        
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
