package evaluation;

import java.util.HashMap;
import java.util.Map;
import value.Value;

public class Environment {

	private Map<String, Value> variableAnswer;
	
	public Environment() {
		variableAnswer = new HashMap<>(); 
	}
	
	public void addAnswer(String variable, Value answer) {
		variableAnswer.put(variable, answer);
	}
		
	public Map<String, Value> getAnswers() {
		return variableAnswer;
	}

	public Value getAnswer(String variable) {
		if (isAnswered(variable)) {
			return variableAnswer.get(variable);
		}
		return null; // TODO throw here?
	}
	
	public boolean isAnswered(String variable) {
		return variableAnswer.containsKey(variable);
	}
}
