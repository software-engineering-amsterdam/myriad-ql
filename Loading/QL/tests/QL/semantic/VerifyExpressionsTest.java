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

class VerifyExpressionsTest {

    @Test
    public void unreferencedVariable() {
        try {
            InputStream src = new FileInputStream("QL/tests/assets/unreferenced.ql");
            ANTLRInputStream input = new ANTLRInputStream(src);

            QLLexer lexer = new QLLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokens);
            Form form = parser.form().result;
            Analyzer Analyzer = new Analyzer();

            Faults faults = Analyzer.analyze(form);

            assertEquals("Error: The variable: Name3 is not defined on line 5", faults.getErrors().get(0).show());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}