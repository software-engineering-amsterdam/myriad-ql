package ui.field;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ast.type.BooleanType;
import ast.type.Type;
// import javafx.scene.control.Control;
import javafx.scene.control.Control;
import ui.Notifier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import value.BoolValue;
import value.StringValue;
import value.Value;

public class Check extends CheckBox implements Field {

	private Notifier listener;
	private String name;
	
	public Check(String name) {
		super();
		this.name = name;
		onChanged();
	}
	
	@Override
	public void addListener(Notifier listener) {
		this.listener = listener;
	}
	
	@Override
	public Value getAnswer() {
		return new BoolValue(isSelected());
	}
	
	@Override
	public void setAnswer(Value value) {
		setSelected((boolean) value.getValue());	
	}
	
	public void onChanged() {
		
		selectedProperty().addListener(new ChangeListener<Boolean>()  {
           
			@Override
           public void changed(ObservableValue<? extends Boolean> observable,
                               Boolean oldValue, Boolean newValue) {
           	
	       		listener.someoneSaidHello(name, new BoolValue(newValue));
            }
		});
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
