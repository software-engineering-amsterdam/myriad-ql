package QL.ui.message;

import QL.message.Message;
import javafx.scene.control.Alert;

import java.util.List;

public class MessageDialog {

	public MessageDialog(List<Message> messages) {
		createDialog(messages);
	}

	private void createDialog(List<Message> messages) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information dialog");
		alert.setHeaderText(null);

		String body = "";
		for (Message msg : messages) {
			body += msg.getBody() + "\n\n";
		}

		alert.setContentText(body);
		alert.showAndWait();
	}
	
}
