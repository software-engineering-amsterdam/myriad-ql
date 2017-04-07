package sc.ql.gui;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
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
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.setPreferredSize(new Dimension(1000, 800));
		
		if (this.messages.isEmpty()) {
			System.out.println("build form");
			BuildComponents buildComponents = new BuildComponents(this.form, new HashMap<String, Value>(), panel);
			panel = buildComponents.getPanel();
		}
		else {
			panel = buildMessages(panel);
		}
		
		JFrame frame = new JFrame("QL Form: "+this.form.getFormName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
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
