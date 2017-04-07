package sc.ql.gui.widgets;

import java.awt.Component;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class MoneyWidget implements Widget {
	private final Component component;
	
	public MoneyWidget(JPanel panel) {
		DecimalFormat format = new DecimalFormat("#,##0.00");
		
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(BigDecimal.class);
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
