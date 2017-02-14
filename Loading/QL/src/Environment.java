import java.util.HashMap;
import java.util.Map;

import ast.atom.Atom;

public class Environment {
	
	Map<String, Atom> atoms;
	// TODO save answers questionnaire
	Map<String, String> answers;
	
	Environment() {
//		atoms = new HashMap<String, Atom>(); // TODO do we want a hashmap?
		answers = new HashMap<>();
	}
	
	public void addAnswer(String question, String answer) {
		
		if (answers.containsKey(question)) {
			// TODO THROW you have a double question
		}		
		answers.put(question, answer);
	}
	
	public Map<String, String> getAnswers() {
		return answers;
	}
	
}
