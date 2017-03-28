package test.org.uva.taxfree.ql.ast;

import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.ast.QlAstBuilder;
import org.uva.taxfree.ql.gui.MessageList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AstBuilderTest {
    @Test
    public void testValidForms() throws Exception {
        File validFilesDir = new File("src\\test\\org\\uva\\taxfree\\ql\\testFiles\\validForms");
        MessageList semanticsMessages = new MessageList();

        System.out.println("  Testing valid forms at path: " + validFilesDir.getAbsolutePath());
        boolean passedAllTests = true;
        for (File file : validFilesDir.listFiles()) {
            System.out.println("    - Input file: " + file.getName());
            QlAstBuilder builder = new QlAstBuilder(file);
            builder.generateAst(semanticsMessages);
        }
        if (semanticsMessages.hasMessages()) {
            throw new TestException("Some tests failed!");
        }
        System.out.println("  All tests successful!");
    }

    @Test
    public void testInvalidForms() throws Exception {
        File validFilesDir = new File("src\\test\\org\\uva\\taxfree\\ql\\testFiles\\invalidForms");
        MessageList semanticsMessages = new MessageList();

        System.out.println("  Testing invalid forms at path: " + validFilesDir.getAbsolutePath());
        List<String> parseErrors = new ArrayList<>();
        File[] files = validFilesDir.listFiles();
        for (File file : files) {
            int messageSize = 0;
            try {
                QlAstBuilder builder = new QlAstBuilder(file);
                messageSize = semanticsMessages.messageAmount();
                builder.generateAst(semanticsMessages);
            } catch (UnsupportedOperationException e) {
                Assert.assertTrue(messageSize < semanticsMessages.messageAmount());
            }
        }
        if (semanticsMessages.messageAmount() != files.length) {
            System.out.println(parseErrors);
            throw new TestException("  Failed! Some invalid forms were parsed!:");
        }
        System.out.println("  Success! No invalid forms were silently parsed!");
    }
}