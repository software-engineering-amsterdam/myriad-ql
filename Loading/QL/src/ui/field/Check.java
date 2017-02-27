package ui.field;

import java.util.function.Function;

import ast.type.BooleanType;
import ast.type.Type;
// import javafx.scene.control.Control;
import javafx.scene.control.Control;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import value.BoolValue;
import value.StringValue;
import value.Value;

public class Check extends CheckBox implements Field {

	@Override
	public Value getAnswer() {
		return new BoolValue(isSelected());
	}
	
	@Override
	public void setAnswer(Value value) {
		setSelected((boolean) value.getValue());	
	}

	@Override
	public Boolean isChanged() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// @Override
//	public String setOnChange(Function<String, String> func) {
//    	field.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable,
//                                String oldValue, String newValue) {
//            	func.apply(newValue);
//            	return newValue;
//            }
//    	}); 		
//	}

////	@Override
////	public Control getControl(BooleanType type) { 
////		return new CheckBox(); 
////	}
//
//	@Override
//	<T = CheckBox>
//	public CheckBox getControl(Type type) {
//		// TODO Auto-generated method stub
//		return new CheckBox();
//	}

}
