package sc.ql.gui.widgets;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import sc.ql.gui.BuildComponents;
import sc.ql.gui.values.StringValue;
import sc.ql.gui.values.Value;

public class StringWidget implements Widget {
	private final Component component;
	
	public StringWidget(BuildComponents buildComponents, String questionId, Value value) {
		JTextField field = new JTextField(30);
		field.setText(value.toString());
		
		field.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) { }

			@Override
			public void focusLost(FocusEvent event) {
				String input = field.getText();
				StringValue value = new StringValue(input);
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
