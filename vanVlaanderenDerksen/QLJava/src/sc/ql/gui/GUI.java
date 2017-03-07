package sc.ql.gui;

import net.miginfocom.swing.MigLayout;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sc.ql.model.Form;

public class GUI extends JPanel {
	private static final int width = 1000;
	private static final int height = 800;
	private static final Font font = new Font("Arial", Font.PLAIN, 30);
	
	public GUI(Form form) {
		this.setLayout(new MigLayout());
		this.setFont(new Font("Arial", Font.PLAIN, 30));
		
		JLabel nameLabel = new JLabel("test");
		JTextField fNameField = new JTextField(30);
		
		add(nameLabel);
		add(fNameField, "wrap");
		
		JLabel condLabel = new JLabel("Conditional label");
		JTextField fCondField = new JTextField(30);
	
		add(condLabel).setVisible(false);
		add(fCondField).setVisible(false);
		
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
					add(fCondField).setVisible(false);
				}
				else {
					add(condLabel).setVisible(true);
					add(fCondField).setVisible(true);
				}
			}
		});
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}
