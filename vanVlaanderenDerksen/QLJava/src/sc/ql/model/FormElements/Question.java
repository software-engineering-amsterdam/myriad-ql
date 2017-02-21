package sc.ql.model.FormElements;

import sc.ql.model.ID;
import sc.ql.model.Atom;
import sc.ql.model.FormElement;

public class Question implements FormElement {
	private final String question; 
	private final ID id;
	private final Atom.Type type;
	
	public Question(String question, ID id, Atom.Type type) {
		this.question = question;
		this.id = id;
		this.type = type;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public ID getId() {
		return this.id;
	}
	
	public Atom.Type getType() {
		return this.type;
	}
}