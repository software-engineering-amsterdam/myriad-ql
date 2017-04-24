package sc.ql.gui.widgets;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

import sc.ql.gui.BuildComponents;
import sc.ql.gui.values.MoneyValue;
import sc.ql.gui.values.Value;

public class MoneyWidget implements Widget {
	private final Component component;
	
	public MoneyWidget(BuildComponents buildComponents, String questionId, Value value) {
		DecimalFormat format = new DecimalFormat("#,##0.00");
		
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(BigDecimal.class);
		formatter.setAllowsInvalid(false);
		
		JFormattedTextField field = new JFormattedTextField(formatter);
		field.setFont(new Font("Arial", Font.PLAIN, 18));
		field.setValue(value.toMoney());
		field.setColumns(10);
		
		field.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) { }

			@Override
			public void focusLost(FocusEvent event) {
				String input = field.getText();
				BigDecimal newValue = new BigDecimal(input.replaceAll("\\.", "").replaceAll(",", "."));
				MoneyValue value = new MoneyValue(newValue);
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
