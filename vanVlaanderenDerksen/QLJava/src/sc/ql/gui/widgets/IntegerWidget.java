package sc.ql.gui.widgets;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

import sc.ql.gui.BuildComponents;
import sc.ql.gui.values.IntegerValue;
import sc.ql.gui.values.Value;

public class IntegerWidget implements Widget {
	private final Component component;
	
	public IntegerWidget(BuildComponents buildComponents, String questionId, Value value) {
		NumberFormat format = NumberFormat.getInstance();
		
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setAllowsInvalid(false);
		
		JFormattedTextField field = new JFormattedTextField(formatter);
		field.setValue(value.toInteger());
		field.setColumns(10);
		
		field.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) { }

			@Override
			public void focusLost(FocusEvent event) {
				String input = field.getText();
				Integer newValue = Integer.parseInt(input.replaceAll("\\.", ""));
				IntegerValue value = new IntegerValue(newValue);
				buildComponents.updatePanel(questionId, value);
			}
			
		});
		
		this.component = field;
	}
	
	@Override
	public Component getComponent() {
		return this.component;
	}
	
}
