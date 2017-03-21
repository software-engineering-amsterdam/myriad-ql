package sc.ql.gui;

import net.miginfocom.swing.MigLayout;

import java.awt.Dimension;
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
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new MigLayout());
		jPanel.setPreferredSize(new Dimension(1000, 800));
		
		if (this.messages.isEmpty()) {
			jPanel = this.form.accept(new BuildComponents(jPanel, new HashMap<String, Value>()));
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
	
	/*private void buildForm() {
		
		JLabel nameLabel = new JLabel("test");
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		JTextField fNameField = new JTextField(30);
		fNameField.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		add(nameLabel);
		add(fNameField, "wrap");
		
		JLabel condLabel = new JLabel("Conditional label");
		condLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		JTextField condField = new JTextField(30);
		condField.setFont(new Font("Verdana", Font.PLAIN, 14));
	
		add(condLabel).setVisible(false);
		add(condField).setVisible(false);
		
		fNameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				showHide(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				showHide(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				showHide(e);
			}
			
			private void showHide(DocumentEvent e) {
				if(e.getDocument().getLength() == 0) {
					add(condLabel).setVisible(false);
					add(condField).setVisible(false);
				}
				else {
					add(condLabel).setVisible(true);
					add(condField).setVisible(true);
				}
			}
		});
	}
	*/
	
}
