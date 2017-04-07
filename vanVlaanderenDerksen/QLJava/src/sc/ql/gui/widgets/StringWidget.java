package sc.ql.gui.widgets;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTextField;

import sc.ql.gui.BuildComponents;
import sc.ql.gui.values.Value;

public class StringWidget implements Widget {
	private final Component component;
	
	public StringWidget(BuildComponents buildComponents, String questionId, Value value) {
		JTextField field = new JTextField(30);
		this.component = field;
	}
	
	@Override
	public Component getComponent() {
		return this.component;
	}
	
}
