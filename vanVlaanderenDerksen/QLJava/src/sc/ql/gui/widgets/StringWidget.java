package sc.ql.gui.widgets;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class StringWidget implements Widget {
	private final Component component;
	
	public StringWidget(JPanel panel) {
		JTextField field = new JTextField(30);
		this.component = field;
	}
	
	@Override
	public Component getComponent() {
		return this.component;
	}
	
}
