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

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckCyclicDependenciesTest {
    @Test
    public void cyclicdependencies() {
        Form form = createForm("QL/tests/assets/cyclicdependencies.ql");
        Analyzer Analyzer = new Analyzer();

        Faults faults = Analyzer.analyze(form);

        assertEquals("Error: There is a cyclic dependency in the computed questions Name1 and Name0 on line 2", faults.getErrors().get(0).show());
        assertEquals(0, faults.getWarnings().size());
    }

    // TODO cannot make this accessible to other test???
    public Form createForm(String file) {

        try {
            InputStream src = new FileInputStream(file);
            ANTLRInputStream input = new ANTLRInputStream(src);

            QLLexer lexer = new QLLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokens);
            Form form = parser.form().result;
            return form;
        } catch (IOException e) {
            // TODO what to do here?
            return null;
        }
    }}