package sc.ql.model.types;

import java.awt.Component;
import javax.swing.JTextField;

public class StringType extends Type {

	@Override
	public String toString() {
		return "String";
	}
	
	@Override
	public Boolean isStringType() {
		return true;
	}
	
	@Override
	public Boolean isCompatibleWith(Type type) {
		return type.isCompatibleWith(this);
	}
	
	@Override
	public Boolean isCompatibleWith(StringType type) {
		return true;
	}
	
	@Override 
	public Component getWidget() {
		JTextField field = new JTextField(30);
		return field;
	}
}
