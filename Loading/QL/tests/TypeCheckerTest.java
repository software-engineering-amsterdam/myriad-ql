import QL.Faults;
import QL.ast.Form;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import QL.semantic.Analyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeCheckerTest {

    @Test
    public void duplicateLabel() {
        try {
            InputStream src = new FileInputStream("QL/tests/assets/typechecker.ql");
            ANTLRInputStream input = new ANTLRInputStream(src);

            QLLexer lexer = new QLLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokens);
            Form form = parser.form().result;
            Analyzer Analyzer = new Analyzer();

            Faults faults = Analyzer.analyze(form);

//            assertEquals("Warning: The question: \"Question\" exists twice in the questionnaire on line 2",
//                    warnings.get(0).show());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void duplicateVariable() {
        try {
            InputStream src = new FileInputStream("QL/tests/assets/typechecker2.ql");
            ANTLRInputStream input = new ANTLRInputStream(src);

            QLLexer lexer = new QLLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokens);
            Form form = parser.form().result;
            Analyzer Analyzer = new Analyzer();

            Faults faults = Analyzer.analyze(form);

//            assertEquals("Warning: The variable Name0 cannot be added, because it is already defined on line 2",
//                    warnings.get(0).show());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void unreferencedVariable() {
        try {
            InputStream src = new FileInputStream("QL/tests/assets/typechecker3.ql");
            ANTLRInputStream input = new ANTLRInputStream(src);

            QLLexer lexer = new QLLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokens);
            Form form = parser.form().result;
            Analyzer Analyzer = new Analyzer();

            Faults faults = Analyzer.analyze(form);

//            assertEquals("Warning: The variable: Name3 is not defined on line 5",
//                    warnings.get(0).show());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
