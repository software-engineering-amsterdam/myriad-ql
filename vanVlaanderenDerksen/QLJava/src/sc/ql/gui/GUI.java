package sc.ql.gui;

import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sc.ql.gui.values.Value;
import sc.ql.checkform.Message;
import sc.ql.model.Form;

public class GUI {
	private final Form form; 
	private final List<Message> messages;
	
	public GUI(Form form, List<Message> messages) {
		this.form = form;
		this.messages = messages;
	}
	
	public void launchGUI() {
		JPanel jPanel = null;
		
		if (this.messages.isEmpty()) {
			BuildComponents buildComponents = new BuildComponents(this.form, new HashMap<String, Value>());
			jPanel = buildComponents.getPanel();
		}
		else {
			jPanel = buildMessages(jPanel);
		}
		
		JFrame frame = new JFrame("QL Form: "+this.form.getFormName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(jPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	private JPanel buildMessages(JPanel jPanel) {
		for (Message message : this.messages) {
			JLabel jLabel = new JLabel(message.toString());
			jPanel.add(jLabel, "wrap");
		}
		
		return jPanel;
	}

}
