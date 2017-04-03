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
	private MenuItem yes;
	private MenuItem no;

	public DropDownMenu(String name, String trueText, String falseText, Notifier notifier, BoolValue value) {
					
		String activeText = trueText;
		if (!value.getValue()) {
			activeText = falseText;
		}
		
		this.field = new MenuButton(activeText);
		
		yes = new MenuItem(trueText);
		no = new MenuItem(falseText);
		field.getItems().addAll(yes, no);
		
		field.setId(name);
		
		yes.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	yes.setVisible(true);
		    	no.setVisible(false);
		        notifier.updateQuestionnaire(name, new BoolValue(true));
		    }
		});
		
		no.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	yes.setVisible(false);
		    	no.setVisible(true);
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

	@Override
	public void setValue(Value value) {
		// TODO implement
		
	}

}
