package sc.ql.model.types;

import javax.swing.JPanel;

import sc.ql.gui.widgets.BooleanWidget;
import sc.ql.gui.widgets.Widget;

public class BooleanType extends Type {

	@Override
	public String toString() {
		return "Boolean";
	}
	
	@Override
	public Boolean isBooleanType() {
		return true;
	}

	@Override
	public Boolean isCompatibleWith(Type type) {
		return type.isCompatibleWith(this);
	}
	
	@Override
	public Boolean isCompatibleWith(BooleanType type) {
		return true;
	}
	
	@Override
	public Widget getWidget(JPanel panel) {
		return new BooleanWidget(panel);
	}
	
}
