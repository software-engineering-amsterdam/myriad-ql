package ui;

import ast.type.Type;
import value.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

public class QuestionnaireQuestion {
	
	private String name;
	private String label;
	private Type type;
	private Control entryField;
	
	public QuestionnaireQuestion(String name, String label, Type type) {
		this.name = name;
		this.label = label;
		this.type = type;
		this.entryField = deriveField(type);
	}
	
	// TODO move to field
	private Control deriveField(Type type) {
        if ("string" == type.getType()) {
        	return new TextField();
        } else if ("boolean" == type.getType()) {
        	return new CheckBox();
        } else if ("integer" == type.getType()) {
        	TextField field = new TextField();
        	field.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        (field).setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
        	return field;
        } else {
        	// TODO error
        	return null;
        }        	
	}
	
	
	// TODO move to field
	public Value getAnswer() {
		if ("boolean" == type.getType()) {
			return new BoolValue(((CheckBox) entryField).isSelected());
		} else {
			String str = ((TextField) entryField).getText();
			if (str.isEmpty()) {
				return new EmptyValue();
			} else if ("integer" == type.getType()) {
				return new StringValue(str);
			} else {
				return new IntegerValue(Integer.valueOf(str));
			}
		
		}
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
	
	public Control getEntryField() {
		return entryField;
	}
	
}
