package sc.ql.gui.widgets;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import sc.ql.gui.values.BooleanValue;

public class BooleanWidget implements Widget {
	private final Component component;
	public BooleanValue value;
	
	public BooleanWidget(JPanel panel) {
		value = new BooleanValue(false);
		
		JCheckBox checkbox = new JCheckBox();
		checkbox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JCheckBox cb = (JCheckBox) event.getSource();
		        if (cb.isSelected()) {
		            System.out.println("checkbox checked");
		            value = new BooleanValue(true);
		        } else {
		        	System.out.println("checkbox unchecked");
		        	value = new BooleanValue(false);
		        }
		        
		        panel.revalidate();
		    }
		});
		
		this.component = checkbox;
	}
	
	@Override
	public Component getComponent() {
		return this.component;
	}
	
}
