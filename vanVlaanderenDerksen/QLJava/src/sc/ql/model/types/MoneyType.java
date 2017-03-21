package sc.ql.model.types;

import java.awt.Component;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class MoneyType extends Type {
	
	@Override
	public String toString() {
		return "Money";
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
	public Boolean isCompatibleWith(MoneyType type) {
		return true;
	}
	
	@Override 
	public Component getWidget() {
		DecimalFormat format = new DecimalFormat("#,##0.00");
		
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(BigDecimal.class);
		formatter.setAllowsInvalid(false);
		
		JFormattedTextField field = new JFormattedTextField(formatter);
		field.setValue(0);
		field.setColumns(10);
		
		return field;
	}
	
}
