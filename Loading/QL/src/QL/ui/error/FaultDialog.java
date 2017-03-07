package QL.ui.error;

import java.util.List;

import QL.Fault;
import javafx.scene.control.Alert;

public abstract class FaultDialog  {

	public abstract void show();
	
	protected <T extends Fault> void show(List<T> faults, Alert dialog) {
		String message = "";
		for (Fault fault : faults) {
			message += fault.show() + "\n\n";
		}
		
		if (message.equals("")) {
			throw new AssertionError("Shows the fault dialog without any faults");
		}
		
		dialog.setContentText(message);
		
		dialog.showAndWait();
	}
	
}
