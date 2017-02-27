package ui.field;

import java.util.function.Function;

import ast.type.BooleanType;
import ast.type.IntegerType;
import ast.type.StringType;
import ast.type.Type;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import ui.Notifier;
import value.StringValue;
import value.Value;


public interface Field {

	// public abstract <T extends Control> T getControl(Type type);
	
	public Value getAnswer();
	
	public void setAnswer(Value value);
	
	public void addListener(Notifier toAdd);
	
//	public <V extends Value, T extends Type> V getAnswer(T type) {
//		return null;
//	}
//	
//	public Control getControl() {
//		return null;
//	}
//	
//	public <T extends Type> void getAnswer2(T type) {
//		
//	}

	
//	public Field(Type type) {
//		CheckField((BooleanType) type);
//	}
	

	
//	public Field(BooleanType type) {
//		this.field = new CheckBox();
//	}
//	
//	public Field(StringType type) {
//		this.field = new TextField();
//	}
//	
//	public Field(IntegerType type) {
//		this.field = new TextField();
//	}
	
	// Now you have to use switch in EVERY function:(
//	public Field(Type type) {
//		switch(type.getType()) {
//			case("string") :
//				this.field = new TextField();
//				break;
//			case("integer") :
//				this.field = new TextField();
//				break;
//			case("boolean") :
//				this.field = new CheckBox();
//				break;
//			default: 
//				// throw RuntimeException("Undefined type: " + type.getType() +
//				//		", while creating input fields for the questtionaire.")
//				// TODO throw error
//				break;
//		}
//	}
	
//	public Value getAnswer() {
//		
//		return new StringValue();
//	}
//	
//	public static Control getControl(BooleanType type) { 
//		return new CheckBox(); 
//	}
//	
//	public static Control getControl(StringType type) { 
//		return new TextField();
//	}
//	
//	public static Control getControl(IntegerType type) { 
//		
//    	TextField field = new TextField();
//    	field.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    (field).setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });
//    	return field;
//    }
}
