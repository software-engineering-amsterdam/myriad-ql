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
	
	public Value getAnswer();
	
	public void setAnswer(Value value);
	
	public void addListener(Notifier listener);
	
}
