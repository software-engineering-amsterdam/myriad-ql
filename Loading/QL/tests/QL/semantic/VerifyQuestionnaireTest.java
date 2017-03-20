package QL.semantic;

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

class VerifyQuestionnaireTest {
    @Test
    public void validQuestionnaire() throws IOException {
        Form form = createForm("QL/tests/assets/validquestionnaire.ql");
        Analyzer analyzer = new Analyzer();
        analyzer.analyze(form);

        assertEquals(true, analyzer.getMessages().isEmpty());
    }

    @Test
    public void cyclicDependencies() throws IOException {
        Form form = createForm("QL/tests/assets/cyclicdependencies.ql");
        Analyzer analyzer = new Analyzer();
        analyzer.analyze(form);

        assertEquals(false, analyzer.getMessages().isEmpty());
        assertEquals(1, analyzer.getMessages().size());
        assertEquals("Error: There is a cyclic dependency in the computed questions Name1 and Name0 on line 2", analyzer.getMessages().get(0).getBody());
    }

    @Test
    public void duplicateLabel() throws IOException {
        Form form = createForm("QL/tests/assets/duplicatelabel.ql");
        Analyzer analyzer = new Analyzer();
        analyzer.analyze(form);

        assertEquals(false, analyzer.getMessages().isEmpty());
        assertEquals(1, analyzer.getMessages().size());
        assertEquals("Warning: The question: \"Question\" exists twice in the questionnaire on line 2", analyzer.getMessages().get(0).getBody());
    }

    @Test
    public void duplicateVariable() throws IOException {
        Form form = createForm("QL/tests/assets/duplicatevariable.ql");
        Analyzer analyzer = new Analyzer();
        analyzer.analyze(form);

        assertEquals(false, analyzer.getMessages().isEmpty());
        assertEquals(1, analyzer.getMessages().size());
        assertEquals("Error: The variable Name0 cannot be added, because it is already defined on line 2", analyzer.getMessages().get(0).getBody());
    }

    @Test
    public void unreferencedVariable() throws IOException {
        Form form = createForm("QL/tests/assets/unreferenced.ql");
        Analyzer analyzer = new Analyzer();
        analyzer.analyze(form);

        assertEquals(false, analyzer.getMessages().isEmpty());
        assertEquals(2, analyzer.getMessages().size());
        assertEquals("Error: The variable: Name3 is not defined on line 5", analyzer.getMessages().get(0).getBody());
    }


    public Form createForm(String file) throws IOException {
        InputStream src = new FileInputStream(file);
        ANTLRInputStream input = new ANTLRInputStream(src);

        QLLexer lexer = new QLLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        Form form = parser.form().result;
        return form;
    }
}