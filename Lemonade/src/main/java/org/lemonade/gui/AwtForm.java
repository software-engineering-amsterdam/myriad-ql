package org.lemonade.gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FilenameFilter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.lemonade.QLLexer;
import org.lemonade.QLParser;
import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.binary.EqBinary;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.visitors.EvaluateVisitor;
import org.lemonade.visitors.FormVisitor;
//import org.lemonade.visitors.QLFormVisitor;

public class AwtForm {

    private Frame mainFrame;
    private Label headerLabel;
    private Label statusLabel;
    private Panel contentPanel;
    private Panel controlPanel;

    public AwtForm() {
        mainFrame = new Frame("Form");
        mainFrame.setSize(600, 500);
        mainFrame.setLayout(new GridLayout(4, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new Label();
        headerLabel.setAlignment(Label.CENTER);
        statusLabel = new Label();
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setSize(350, 100);

        contentPanel = new Panel();
        contentPanel.setLayout(new GridLayout(0, 2));

        controlPanel = new Panel();
        controlPanel.setLayout(new GridLayout(1, 2));

        mainFrame.add(headerLabel);
        mainFrame.add(contentPanel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        AwtForm awtForm = new AwtForm();
        awtForm.selectQuestionnaire();
    }

    private void selectQuestionnaire() {
        FileDialog fd = new FileDialog(mainFrame, "Select a questionnaire file");
        FilenameFilter filter = (dir, name) -> name.endsWith(".ql");
        fd.setFilenameFilter(filter);

        Button showFileDialogButton = new Button("Open file");
        showFileDialogButton.addActionListener(e -> {
            fd.setVisible(true);
            statusLabel.setText("File selected: " + fd.getFile());
        });

        Button submitFileButton = new Button("Submit file");
        submitFileButton.addActionListener(e -> {
            String contents = openQuestionnaireFile(fd.getDirectory(), fd.getFile());
            if (contents != null)
                parseContents(contents);
            else
                System.err.println("Empty or corrupt file");
        });

        controlPanel.add(showFileDialogButton);
        controlPanel.add(submitFileButton);
        mainFrame.setVisible(true);
    }

    private String openQuestionnaireFile(String dir, String fileName) {
        try {
            String contents = String.join("\n", Files.readAllLines(Paths.get(dir + fileName)));
            System.out.println(contents);
            return contents;
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            return null;
        }
    }

    private void showQuestions(String fileContents) {
        controlPanel.removeAll();
        TextArea textArea = new TextArea();
        textArea.setSize(600, 400);
        textArea.append(fileContents);
        contentPanel.add(textArea);
        mainFrame.setVisible(true);
    }

    private void parseContents(String fileContents) {
        try {
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(fileContents));

            QLLexer lexer = new QLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokens);
            ParseTree tree = parser.form();
            FormVisitor visitor = new FormVisitor();

            Form root = (Form) tree.accept(visitor);

            // Set title of form
            headerLabel.setText(root.getIdentifier().getValue());
            controlPanel.removeAll();

            for (Body body : root.getBodies()) {
                if (body.isQuestion()) {
                    Question question = (Question) body;

                    Label label = new Label(question.getLabel());
                    contentPanel.add(label);

                    if (question.getType().isBoolean()) {
                        Panel checkboxPanel = new Panel(new GridLayout(1, 2));
                        CheckboxGroup group = new CheckboxGroup();
                        Checkbox trueButton = new Checkbox("True", group, false);
                        Checkbox falseButton = new Checkbox("False", group, false);
                        checkboxPanel.add(trueButton);
                        checkboxPanel.add(falseButton);
                        contentPanel.add(checkboxPanel);
                    } else if (question.getType() instanceof QLStringType) {
                        TextField field = new TextField();
                        contentPanel.add(field);
                    }
                } else if (body.isConditional()) {
                    Conditional conditional = (Conditional) body;
                    if (conditional.getCondition() instanceof EqBinary) {

                    }
                }
            }

            mainFrame.setVisible(true);

            EvaluateVisitor eval = new EvaluateVisitor();
            root.accept(eval);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }
}
