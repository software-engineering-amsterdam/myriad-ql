package ui;

import java.util.function.Function;

import ast.type.Type;
import value.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import ui.field.Check;
import ui.field.Field;
import ui.field.Text;
import ui.field.Number;

public class QuestionnaireQuestion {
	
	private String name;
	private String label;
	private Type type;
	private QControl entryField;
	
	public QuestionnaireQuestion(String name, String label, Type type) {
		this.name = name;
		this.label = label;
		this.type = type;
		this.entryField = deriveField(type);
	}
	
	// TODO move to field
	private QControl deriveField(Type type) {
        if ("string" == type.getType()) {
        	return new QControl(new ui.field.Text());
        } else if ("boolean" == type.getType()) {
        	return new QControl(new ui.field.Check(name));
        } else if ("integer" == type.getType()) {
        	return new QControl(new ui.field.Number());
        } else {
        	System.out.println("unknown type!");
        	return null;
        }        	
	}
	
	
	// TODO move to field
	// TODO default return statement
	public Value getAnswer() {
		
		return entryField.getAnswer();
		
//		if ("boolean" == type.getType()) {
//			return new BoolValue(((CheckBox) entryField).isSelected());
//		} else {
//			String str = ((TextField) entryField).getText();
//			if (str.isEmpty()) {
//				return new EmptyValue();
//			} else if ("string" == type.getType()) {
//				return new StringValue(str);
//			} else {
//				return new IntegerValue(Integer.valueOf(str));
//			}
//		
//		}
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
	
	public QControl getEntryField() {
		return entryField;
	}
	
}
