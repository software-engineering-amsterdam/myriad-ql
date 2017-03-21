package org.uva.taxfree.ql.main;

import org.uva.taxfree.ql.ast.AstBuilder;
import org.uva.taxfree.ql.gui.*;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.blocks.FormNode;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void mainOld(String[] args) throws IOException {
        File inputFile = FileSelector.select();
        if (!inputFile.exists()) {
            if (MessageWindow.retryDialog(new ErrorMessage("No file selected...\nRetry?"))) {
                main(args);
            }
            return;
        }

        AstBuilder builder = new AstBuilder(inputFile);
        FormNode ast = builder.generateTree();
        SymbolTable symbolTable = new SymbolTable();
        MessageList semanticsMessages = new MessageList();
        ast.fillSymbolTable(symbolTable);
        ast.checkSemantics(symbolTable, semanticsMessages);

        if (semanticsMessages.hasMessages()) {
            MessageWindow.showMessages(semanticsMessages);
        }

        if (!semanticsMessages.fatalErrors()) {
            QuestionForm taxForm = new QuestionForm(ast.toString(), symbolTable);
            ast.fillQuestionForm(taxForm);
            taxForm.show();
        }
    }

    public static void main(String[] args) throws IOException {
//        showEditor();
        mainOld(args);
    }

    private static void showEditor() {
        // The frame
        JFrame jFrame = new JFrame("TaxFree editor");
        jFrame.setSize(500, 500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // The panel
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.RED);
        jPanel.setLayout(new BorderLayout());
        jPanel.setBorder(new LineBorder(Color.white, 5));
        // The textarea
        JTextArea jTextArea = new JTextArea("form exampleForm {\r\n\t\r\n}");
        jTextArea.setCaretPosition(21); // Default position: start in middle of the form
        jTextArea.setTabSize(4);
        jTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                mInputText = jTextArea.getText();
                jTextArea.getHighlighter().removeAllHighlights();
                tryRender();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mInputText = jTextArea.getText();
                jTextArea.getHighlighter().removeAllHighlights();
                tryRender();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("AAA");
            }
        });
        // Highlighting test
        Highlighter highlighter = jTextArea.getHighlighter();
        Highlighter.HighlightPainter painter =
                new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
//        int p0 = jTextArea.getText().indexOf("form");
//        int p1 = p0 + "form".length();
        int p0 = 19;
        int p1 = 23;
        try {
            highlighter.addHighlight(p0, p1, painter);
        } catch (BadLocationException e) {
            // Don't highlight then
            e.printStackTrace();
        }

        // The button
        JButton jButton = new JButton("Close");

        // Add data to the panel
        jPanel.add(jTextArea, BorderLayout.CENTER);
        jPanel.add(jButton, BorderLayout.SOUTH);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    private static String mInputText;

    private static Thread mRenderThread;
    private static void tryRender() {
        if (mRenderThread != null) {
            mRenderThread.interrupt();
        }
        mRenderThread = new Thread(() -> {
            try {
                Path filePath = Files.write(Paths.get("./editorinput.txt"), mInputText.getBytes());
                AstBuilder builder = new AstBuilder(filePath.toFile());
                FormNode ast = builder.generateTree();
                showFormForAst(ast);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        });
        mRenderThread.start();
    }

    private static QuestionForm mTaxForm;
    private static void showFormForAst(FormNode ast) {
        SymbolTable symbolTable = new SymbolTable();
        MessageList semanticsMessages = new MessageList();
        ast.fillSymbolTable(symbolTable);
        ast.checkSemantics(symbolTable, semanticsMessages);

        if (semanticsMessages.hasMessages()) {
//            MessageWindow.showMessages(semanticsMessages);
        }

        if (!semanticsMessages.fatalErrors()) {
            mTaxForm = new QuestionForm(ast.toString(), symbolTable);
            ast.fillQuestionForm(mTaxForm );
            mTaxForm .show();
            System.out.println("Success");
        }
    }
}




