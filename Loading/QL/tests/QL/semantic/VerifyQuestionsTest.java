package QL.semantic;

import QL.Faults;
import QL.QLLexer;
import QL.QLParser;
import QL.ast.Form;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class VerifyQuestionsTest {

    @Test
    public void duplicateLabel() {
        try {
            InputStream src = new FileInputStream("QL/tests/assets/duplicatelabel.ql");
            ANTLRInputStream input = new ANTLRInputStream(src);

            QLLexer lexer = new QLLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokens);
            Form form = parser.form().result;
            Analyzer Analyzer = new Analyzer();

            Faults faults = Analyzer.analyze(form);

            assertEquals("Warning: The question: \"Question\" exists twice in the questionnaire on line 2", faults.getWarnings().get(0).show());
            assertEquals(0, faults.getErrors().size());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void duplicateVariable() {
        try {
            InputStream src = new FileInputStream("QL/tests/assets/duplicatevariable.ql");
            ANTLRInputStream input = new ANTLRInputStream(src);

            QLLexer lexer = new QLLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokens);
            Form form = parser.form().result;
            Analyzer Analyzer = new Analyzer();

            Faults faults = Analyzer.analyze(form);

            assertEquals(0, faults.getWarnings().size());
            assertEquals("Error: The variable Name0 cannot be added, because it is already defined on line 2", faults.getErrors().get(0).show());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}