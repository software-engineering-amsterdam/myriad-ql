package ui.error;

import java.util.List;

import QL.Warning;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WarningDialog extends FaultDialog {

	List<Warning> warnings;
	Alert dialog;
	
	public WarningDialog(List<Warning> warnings) {

		this.warnings = warnings;
		this.dialog = new Alert(AlertType.WARNING);
		dialog.setTitle("Warning Dialog");
		
		dialog.setHeaderText(null);	
		
	}

	@Override
	public void show() {
		super.show(warnings, dialog);		
	}
	
}
