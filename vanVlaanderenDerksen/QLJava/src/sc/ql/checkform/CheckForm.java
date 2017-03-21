package sc.ql.checkform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sc.ql.model.Form;
import sc.ql.model.types.Type;

public class CheckForm {
	private final List<Message> messages;
	
	public CheckForm(Form form) {
		Map<String, Type> stringTypes = new HashMap<String, Type>(); 
		QuestionTypeMap questionTypeMap = new QuestionTypeMap();
		stringTypes.putAll(form.accept(questionTypeMap));
		
		List<Message> messages = new ArrayList<Message>();
		TypeChecker typeChecker = new TypeChecker(stringTypes);
		messages.addAll(form.accept(typeChecker));
		
		this.messages = messages;
	}
	
	public List<Message> getMessages() {
		return this.messages;
	}
}
