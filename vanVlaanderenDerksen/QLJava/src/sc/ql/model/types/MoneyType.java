package sc.ql.model.types;

import sc.ql.gui.BuildComponents;
import sc.ql.gui.values.Value;
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
	public Widget getWidget(BuildComponents buildComponents, String questionId, Value value) {
		return new MoneyWidget(buildComponents, questionId, value);
	}
	
}
