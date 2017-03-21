package qls.ui;

import java.util.List;

import QL.ast.Form;
import QL.message.Message;
import QL.ui.Environment;
import QL.ui.Questionnaire;
import qls.ast.Stylesheet;
import qls.evaluation.Evaluator;

public class PrettyQuestionnaire extends Questionnaire {
	
	
    public void main(Form f, Environment env, List<Message> msgs, Stylesheet stylesheet) {
//    	form = f;
//    	environment = env;
//    	messages = msgs;
// 
//        launch();
		Evaluator evaluator = new Evaluator(env, this);
		evaluator.visit(stylesheet);
		
        super.main(f, env, msgs);
    }
	
//	@Override 
//    public void main(Form f, Environment env, List<Message> msgs) {
////    	form = f;
////    	environment = env;
////    	messages = msgs;
//// 
////        launch();
//		Evaluator evaluator = new Evaluator(env, this);
//		evaluator.visit(stylesheet);
//		
//        super.main(f, env, msgs);
//    }

}
