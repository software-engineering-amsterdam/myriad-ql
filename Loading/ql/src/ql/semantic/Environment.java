package ql.semantic;

import ql.ReferenceTable;
import ql.message.Message;

import java.util.ArrayList;
import java.util.List;

class Environment {

	private final ReferenceTable names;
	private final List<Message> messages;
	
	public Environment() {
		this.names = new ReferenceTable();
		this.messages = new ArrayList<>();
	}
	
	ReferenceTable getReferenceTable() {
		return names;
	}

    List<Message> getMessages() {
		return messages;
	}

	void addMessage(Message m) {
        messages.add(m);
    }
}
