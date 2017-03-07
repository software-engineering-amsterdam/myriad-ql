package test.org.uva.taxfree.ast;

import org.testng.TestException;
import org.testng.annotations.Test;
import org.uva.taxfree.ast.AbstractSyntaxTreeBuilder;

import java.io.File;

public class AbstractSyntaxTreeBuilderTest {
    @Test
    public void testValidForms() throws Exception {
        File validFilesDir = new File("src\\test\\org\\uva\\taxfree\\ast\\validForms");

        System.out.println("  Testing valid forms at path: " + validFilesDir.getAbsolutePath());
        boolean passedAllTests = true;
        for (File file : validFilesDir.listFiles()) {
            System.out.println("    - Input file: " + file.getName());
            try {
                AbstractSyntaxTreeBuilder.generateAst(file);
            } catch (UnsupportedOperationException e) {
//                e.printStackTrace();
                passedAllTests = false;
            }
        }
        if (passedAllTests) {
            System.out.println("  All tests successful!");
        } else {
            throw new TestException("Some tests failed!");
        }
    }

    @Test
    public void testInvalidForms() throws Exception {
        File validFilesDir = new File("src\\test\\org\\uva\\taxfree\\ast\\invalidForms");

        System.out.println("  Testing invalid forms at path: " + validFilesDir.getAbsolutePath());
        boolean createdAst = false;
        for (File file : validFilesDir.listFiles()) {
            System.out.println("    - Input file: " + file.getName());
            try {
                AbstractSyntaxTreeBuilder.generateAst(file);
                createdAst = true;
            } catch (UnsupportedOperationException e) {
//                e.printStackTrace();
            }
        }
        if (createdAst) {
            throw new TestException("  Failed! Some invalid forms were parsed!");
        } else {
            System.out.println("  Success! All invalid forms could not be parsed!");
        }
    }
}