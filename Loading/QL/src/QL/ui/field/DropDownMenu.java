package QL.ui.field;

import QL.ui.Notifier;
import QL.value.BoolValue;
import QL.value.Value;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class DropDownMenu implements Field {

	private final MenuButton field;
	private final MenuItem yes;
	private final MenuItem no;

	public DropDownMenu(String name, String trueText, String falseText, Notifier notifier, BoolValue value) {
	
		this.field = new MenuButton();
		
		MenuButton m = new MenuButton();
		yes = new MenuItem(trueText);
		no = new MenuItem(falseText);
		m.getItems().addAll(yes, no);
		
		field.setId(name);
		
		yes.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        notifier.updateQuestionnaire(name, new BoolValue(true));
		    }
		});
		
		no.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        notifier.updateQuestionnaire(name, new BoolValue(false));
		    }
		});	
	}
	
	@Override
	public Value getAnswer() {
		return new BoolValue(yes.isVisible());
	}
	
	
	@Override
	public MenuButton getField() {
		return field;
	}

}
