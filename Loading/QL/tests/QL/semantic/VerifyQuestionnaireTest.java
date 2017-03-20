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

class VerifyQuestionnaireTest {
    @Test
    public void validQuestionnaire() throws IOException {
        Form form = createForm("QL/tests/assets/validquestionnaire.ql");
        Analyzer analyzer = new Analyzer();

//        List<Message> messages = analyzer.analyze(form);

    }

    @Test
    public void cyclicDependencies() throws IOException {
        Form form = createForm("QL/tests/assets/cyclicdependencies.ql");
        Analyzer analyzer = new Analyzer();

//        List<Message> messages = analyzer.analyze(form);

//        assertEquals("Error: There is a cyclic dependency in the computed questions Name1 and Name0 on line 2", faults.getErrors().get(0).show());
//        assertEquals(false, faults.showAndContinue());
//        assertEquals(true, faults.showAndContinue());
    }

    @Test
    public void cyclicDependenciesIf() throws IOException {
        Form form = createForm("QL/tests/assets/cyclicdependenciesifstatement.ql");
        Analyzer analyzer = new Analyzer();

//        List<Message> messages = analyzer.analyze(form);
//        System.out.print(faults.hasErrors());
//        System.out.print(faults.hasWarnings());

//        assertEquals("Error: There is a cyclic dependency in the computed questions Name1 and Name0 on line 2", faults.getErrors().get(0).show());
//        assertEquals(false, faults.showAndContinue());
//        assertEquals(true, faults.showAndContinue());
    }

    @Test
    public void duplicateLabel() throws IOException {
        Form form = createForm("QL/tests/assets/duplicatelabel.ql");
        Analyzer analyzer = new Analyzer();

//        List<Message> messages = analyzer.analyze(form);

//        assertEquals("Warning: The question: \"Question\" exists twice in the questionnaire on line 2", faults.getWarnings().get(0).show());
//        assertEquals(0, faults.getErrors().size());
    }

    @Test
    public void duplicateVariable() throws IOException {
        Form form = createForm("QL/tests/assets/duplicatevariable.ql");
        Analyzer analyzer = new Analyzer();

//        List<Message> messages = analyzer.analyze(form);

//        assertEquals(0, faults.getWarnings().size());
//        assertEquals("Error: The variable Name0 cannot be added, because it is already defined on line 2", faults.getErrors().get(0).show());
    }

    @Test
    public void unreferencedVariable() throws IOException {
        Form form = createForm("QL/tests/assets/unreferenced.ql");
        Analyzer analyzer = new Analyzer();

//        List<Message> messages = analyzer.analyze(form);

//        assertEquals(0, faults.getWarnings().size());
//        assertEquals("Error: The variable: Name3 is not defined on line 5", faults.getErrors().get(0).show());
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