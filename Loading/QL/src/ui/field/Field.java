package ui.field;

import ast.type.BooleanType;
import ast.type.IntegerType;
import ast.type.StringType;
import ast.type.Type;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;


public abstract class Field {

	// public abstract <T extends Control> T getControl(Type type);
	
	public static Control getControl(BooleanType type) { 
		return new CheckBox(); 
	}
	
	public static Control getControl(StringType type) { 
		return new TextField();
	}
	
	public static Control getControl(IntegerType type) { 
		
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
    }
}
