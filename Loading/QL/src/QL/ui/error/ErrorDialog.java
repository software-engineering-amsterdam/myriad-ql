package QL.ui.error;

import java.util.List;

import QL.Error;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorDialog extends FaultDialog {

	List<Error> errors;
	Alert dialog;
	
	public ErrorDialog(List<Error> errors) {

		this.errors = errors;
		this.dialog = new Alert(AlertType.ERROR);
		dialog.setTitle("Error Dialog");
		
		dialog.setHeaderText(null);	
		
	}

	@Override
	public void show() {
		super.show(errors, dialog);		
	}
	
}
