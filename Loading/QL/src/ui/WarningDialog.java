package ui;

import java.util.List;

import QL.Warning;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WarningDialog {
	
	private Alert dialog;
	private List<Warning> warnings;
	
	public WarningDialog(List<Warning> warnings) {

		this.dialog = new Alert(AlertType.WARNING);
		dialog.setTitle("Warning Dialog");
		dialog.setHeaderText(null);
		this.warnings = warnings;
	}
	
	public void show() {
		String message = "";
		for (Warning warning : warnings) {
			message += warning.show() + "\n\n";
		}
		
		if (message.equals("")) {
			throw new AssertionError("Shows the warning dialog without any errors");
		}
		
		dialog.setContentText(message);
		
		dialog.showAndWait();
	}
	
}
