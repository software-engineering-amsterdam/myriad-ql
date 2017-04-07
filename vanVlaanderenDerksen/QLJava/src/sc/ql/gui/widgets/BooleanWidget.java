package sc.ql.gui.widgets;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sc.ql.gui.BuildComponents;
import sc.ql.gui.values.BooleanValue;
import sc.ql.gui.values.Value;

public class BooleanWidget implements Widget {
	private final Component component;
	
	public BooleanWidget(BuildComponents buildComponents, String questionId, Value value) {
		JCheckBox checkbox = new JCheckBox();
		checkbox.setSelected(value.getValue());
		
		checkbox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JCheckBox cb = (JCheckBox) event.getSource();
		        BooleanValue value = cb.isSelected() ? new BooleanValue(true) : new BooleanValue(false);

		        buildComponents.updatePanel(questionId, value);
		    }
		});
		
		this.component = checkbox;
	}
	
	@Override
	public Component getComponent() {
		return this.component;
	}
	
}
