package qls.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import QL.ReferenceTable;
import QL.ast.type.Type;
import QL.message.Message;
import QL.message.Error;

class Environment {
	
	private final Map<String, Boolean> variableCovered;
	
	private final List<Message> messages;
	
	public Environment(Map<String, Type> variableTypes) {
		
		this.variableCovered = new HashMap<>();	
		
		for (String variable : variableTypes.keySet()) {
			variableCovered.put(variable, false);
		}
		this.messages = new ArrayList<>(); // TODO move to analyzing part
	}
	
	public List<Message> getFaults() {
		return messages;
	}
	
	public boolean presentInQL(String name) {
		
		return variableCovered.containsKey(name);
	}
	
	public boolean isCovered(String name) {
		return variableCovered.get(name);
	}
	
	public void setCovered(String name) {
		variableCovered.replace(name, true);
	}
	
	// TODO fault without line number
	public void checkCoverage() {
		for (String name : variableCovered.keySet()) {
			if (!variableCovered.get(name)) {
				messages.add(new Error("The variable " + name + 
						" is not defined in QLS", 0));
			}
		}
	}
	
}
