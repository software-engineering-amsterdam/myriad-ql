package sc.ql.model.types;

import javax.swing.JPanel;

import sc.ql.gui.widgets.MoneyWidget;
import sc.ql.gui.widgets.Widget;

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
	public Widget getWidget(JPanel panel) {
		return new MoneyWidget(panel);
	}
	
}
