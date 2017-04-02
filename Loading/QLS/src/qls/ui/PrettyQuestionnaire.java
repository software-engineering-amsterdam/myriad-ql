package qls.ui;

import java.util.List;

import QL.ast.Form;
import QL.message.Message;
import QL.ui.Environment;
import QL.ui.Questionnaire;
import javafx.stage.Stage;
import qls.ast.Stylesheet;
import qls.evaluation.Evaluator;

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
