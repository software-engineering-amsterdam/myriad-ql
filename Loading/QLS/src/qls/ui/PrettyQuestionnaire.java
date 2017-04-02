package qls.ui;

import QL.ast.Form;
import QL.message.Message;
import QL.ui.Environment;
import QL.ui.Questionnaire;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import qls.ast.Stylesheet;
import qls.evaluation.CollectDefaultValues;
import qls.evaluation.DefaultValues;
import qls.evaluation.Evaluator;

import java.util.List;

public class PrettyQuestionnaire extends Questionnaire {
	
	private static Form form;
	private static Stylesheet stylesheet;
	private static Environment environment;
	private static List<Message> messages;
	
	
    public void main(Form f, Environment env, List<Message> msgs, Stylesheet s) {
    	form = f;
    	environment = env;
    	messages = msgs;
    	stylesheet = s;
    	
    	launch();
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
		Evaluator evaluator = new Evaluator(environment, this);
		evaluator.visit(stylesheet);
    	
    	super.main(form, environment, messages, primaryStage);

    }
	

}
