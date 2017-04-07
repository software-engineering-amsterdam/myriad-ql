package sc.ql.gui.widgets;

import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class IntegerWidget implements Widget {
	private final Component component;
	
	public IntegerWidget(JPanel panel) {
		NumberFormat format = NumberFormat.getInstance();
		
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setAllowsInvalid(false);
		
		JFormattedTextField field = new JFormattedTextField(formatter);
		field.setValue(0);
		field.setColumns(10);
		
		this.component = field;
	}
	
	@Override
	public Component getComponent() {
		return this.component;
	}
	
}
