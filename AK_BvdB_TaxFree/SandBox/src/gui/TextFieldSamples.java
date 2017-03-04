package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Currency;
import java.util.Date;

public class TextFieldSamples {
    private JFrame mFrame;

    public static void main(String[] args) {
        new TextFieldSamples().run();
    }

    private void run() {
        mFrame = generateForm();
        add("Money", MoneyField());
        add("Int", IntField());
        add("Text", TextField());
        add(" Date", DateField());
        show();
    }

    private JFormattedTextField MoneyField() {
        return baseField(Currency.getInstance("EUR"));
    }

    private JFormattedTextField IntField() {
        return baseField(5);
    }

    private JFormattedTextField TextField() {
        return baseField(new String());
    }

    private JFormattedTextField DateField() {
        return baseField(new Date());
    }


    private JFormattedTextField baseField(Object argument) {
        return new JFormattedTextField(argument);
    }

    private JFrame generateForm() {
        JFrame frame = new JFrame("Questionnaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private void add(String description, JComponent comp) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(description));
        panel.add(comp);
        mFrame.add(panel);
    }

    private void show() {
        mFrame.setVisible(true);
        mFrame.setPreferredSize(new Dimension(640, 480));
        mFrame.pack();
        mFrame.toFront();
    }

}
