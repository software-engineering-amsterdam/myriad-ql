package QL.semantic;

import QL.ReferenceTable;
import QL.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Environment {

	private ReferenceTable variables;
	private List<Message> messages;
	
	public Environment() {
		this.variables = new ReferenceTable();
		this.messages = new ArrayList<>();
	}
	
	ReferenceTable getReferenceTable() {
		return variables;
	}

    List<Message> getMessages() {
		return messages;
	}

	void addMessage(Message m) {
        messages.add(m);
    }
}
