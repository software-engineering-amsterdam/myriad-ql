package sc.ql.model.types;

import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class IntegerType extends Type {

	@Override
	public String toString() {
		return "Integer";
	}
	
	@Override
	public Boolean isNumericType() {
		return true;
	}
	
	@Override
	public Boolean isCompatibleWith(Type type) {
		return type.isCompatibleWith(this);
	}
	
	@Override
	public Boolean isCompatibleWith(IntegerType type) {
		return true;
	}
	
	@Override 
	public Component getWidget() {
		NumberFormat format = NumberFormat.getInstance();
		
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setAllowsInvalid(false);
		
		JFormattedTextField field = new JFormattedTextField(formatter);
		field.setValue(0);
		field.setColumns(10);
		
		return field;
	}
	
}
