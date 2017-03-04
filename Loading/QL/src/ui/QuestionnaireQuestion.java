package ui;

import ast.type.Type;
import javafx.scene.control.Control;
import ui.field.Field;
import value.Value;
import ui.Questionnaire.Notifier;

public class QuestionnaireQuestion {

	private String name;
	private String label;
	private Type type;
	private Field entryField;

	public QuestionnaireQuestion(String name, String label, Type type) {
		this.name = name;
		this.label = label;
		this.type = type;
		this.entryField = type.getField(name);
	}

	// TODO move to type or add type visitor
//	private QControl deriveField(Type type) {
//        if ("string" == type.getType()) {
//        	return new QControl(new ui.field.Text(name));
//        } else if ("boolean" == type.getType()) {
//        	return new QControl(new ui.field.Check(name));
//        } else if ("integer" == type.getType()) {
//        	return new QControl(new ui.field.Number(name));
//        } else {
//        	System.out.println("unknown type!");
//        	return null;
//        }
//	}

	// TODO default return statement
	public Value getAnswer() {
		return entryField.getAnswer();
	}

	public void setAnswer(Value value) {
		entryField.setAnswer(value);
	}

	public String getName() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public Type getType() {
		return type;
	}
	
	public void addListener(Notifier listener) {
		entryField.addListener(listener);
	}

	public Control getControl() {
		return entryField.getField();
	}

}
