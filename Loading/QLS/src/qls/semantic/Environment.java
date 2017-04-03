package qls.semantic;

import QL.ReferenceTable;
import QL.ast.type.Type;
import QL.message.Error;
import QL.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Environment {
	
	private final Map<String, Boolean> questionsQL;
	private final ReferenceTable referenceTable;
	private final List<Message> messages;
	
	public Environment(ReferenceTable referenceTable, List<Message> messages) {
		
		this.questionsQL = new HashMap<>();
		this.referenceTable = referenceTable;
		for (String name : referenceTable) {
			questionsQL.put(name, false);
		}
		this.messages = messages; // TODO move to analyzing part
	}

	boolean isPresentQL(String name) {
		
		return questionsQL.containsKey(name);
	}
	
	boolean isPresentQLS(String name) {
		return questionsQL.get(name);
	}
	
	void setPresentQLS(String name) {
		questionsQL.replace(name, true);
	}
	
	// TODO fault without line number
	void checkCoverage() {
		for (String name : questionsQL.keySet()) {
			if (!questionsQL.get(name)) {
				messages.add(new Error("The question " + name +
						" is not defined in QLS", 0));
			}
		}
	}

	Type getType(String name) {
		return referenceTable.getType(name);
	}

	void addMessage(Message m) {
		messages.add(m);
	}
}
