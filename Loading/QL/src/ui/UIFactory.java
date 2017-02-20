package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UIFactory {
    public static void main() {
        final JFrame frame = new JFrame("Form title");
        frame.setLayout(new BorderLayout());

//        frame.add(new JButton(new AbstractAction("Click to add") {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        frame.add(new JLabel("Bla"));
//                        frame.validate();
//                        frame.repaint();
//                    }
//                });
//            }
//        }));
        frame.setLayout(new BorderLayout());
        frame.add(new TopPanel(), BorderLayout.NORTH);
        frame.add(new MiddlePanel(), BorderLayout.CENTER);
        frame.add(new UpdatePanel(), BorderLayout.SOUTH);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        frame.setVisible(true);
    }
}

class TopPanel extends JPanel {

    public TopPanel() {
        setBackground(Color.red);
        add(new JLabel("I'm on top"));
    }

}

class MiddlePanel extends JPanel {

    public MiddlePanel() {
        setLayout(new BorderLayout());
        add(new JScrollPane(new JTable(new DefaultTableModel(new Object[]{"A", "B", "C"}, 5))));
    }

}

class UpdatePanel extends JPanel {

    public UpdatePanel() {
        for (int index = 0; index < 5; index++) {
            add(new JLabel(Integer.toString(index)));
            add(new JTextField(5));
        }
        add(new JButton("Button"));
    }
}

