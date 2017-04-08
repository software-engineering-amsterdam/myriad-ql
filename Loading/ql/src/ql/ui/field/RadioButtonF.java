package ql.ui.field;

import ql.ui.Notifier;
import ql.value.BoolValue;
import ql.value.Value;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class RadioButtonF implements Field {
	
	private final HBox field;
	private final RadioButton trueBtn;
	private final RadioButton falseBtn;
	
	public RadioButtonF(String name, String trueText, String falseText, Notifier notifier, BoolValue value) {
		
		final ToggleGroup group = new ToggleGroup();
		
		this.field = new HBox();
		
		this.trueBtn = new RadioButton(trueText);
		trueBtn.setToggleGroup(group);

		this.falseBtn = new RadioButton(falseText);
		falseBtn.setToggleGroup(group);
		
		field.getChildren().addAll(trueBtn, falseBtn);
		
		field.setId(name);
		
		trueBtn.setSelected(value.getValue());
		falseBtn.setSelected(!value.getValue());
		
		trueBtn.selectedProperty().addListener(
				(observable, oldValue, newValue) -> notifier.updateQuestionnaire(name, new BoolValue(newValue)));
	}
	
	@Override
	public Value getAnswer() {
		return new BoolValue(trueBtn.isSelected());
	}
	
	
	@Override
	public void draw(GridPane grid, int index) {
		grid.add(field, 1, index);
	}

	@Override
	public void setValue(Value value) {
		trueBtn.setSelected(((BoolValue) value).getValue());
	}
}
