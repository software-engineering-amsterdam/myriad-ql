package qls.semantic;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import ql.QLLexer;
import ql.QLParser;
import ql.ReferenceTable;
import ql.ast.Form;
import ql.message.Message;
import qls.ast.Stylesheet;
import qls.qlsLexer;
import qls.qlsParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VerifyStylesheetTest {
    @Test
    void validQuestionnaire() throws IOException {
        Form form = createForm("qls/tests/assets/validquestionnaire.ql");
        Stylesheet stylesheet = createStylesheet("qls/tests/assets/validstylesheet.qls");

        ql.semantic.Analyzer qlAnalyzer = new ql.semantic.Analyzer();
        ReferenceTable referenceTable = qlAnalyzer.analyze(form);

        List<Message> messages = qlAnalyzer.getMessages();

        qls.semantic.Analyzer analyzer = new qls.semantic.Analyzer(referenceTable, messages);

        analyzer.analyze(stylesheet);

        assertEquals(true, messages.isEmpty());
    }

    @Test
    void questionNotCovered() throws IOException {
        Form form = createForm("qls/tests/assets/validquestionnaire.ql");
        Stylesheet stylesheet = createStylesheet("qls/tests/assets/notcovered.qls");

        ql.semantic.Analyzer qlAnalyzer = new ql.semantic.Analyzer();
        ReferenceTable referenceTable = qlAnalyzer.analyze(form);

        List<Message> messages = qlAnalyzer.getMessages();

        qls.semantic.Analyzer analyzer = new qls.semantic.Analyzer(referenceTable, messages);

        analyzer.analyze(stylesheet);

        assertEquals(false, messages.isEmpty());
        assertEquals(1, messages.size());
        assertEquals("Error: [QLS] The question Name1 is not defined in QLS on line 0", messages.get(0).getBody());
    }

    @Test
    void questionNotInQl() throws IOException {
        Form form = createForm("qls/tests/assets/validquestionnaire.ql");
        Stylesheet stylesheet = createStylesheet("qls/tests/assets/notinql.qls");

        ql.semantic.Analyzer qlAnalyzer = new ql.semantic.Analyzer();
        ReferenceTable referenceTable = qlAnalyzer.analyze(form);

        List<Message> messages = qlAnalyzer.getMessages();

        qls.semantic.Analyzer analyzer = new qls.semantic.Analyzer(referenceTable, messages);

        analyzer.analyze(stylesheet);

        assertEquals(false, messages.isEmpty());
        assertEquals(1, messages.size());
        assertEquals("Error: [QLS] The question Name2 appears in the QLS, but does not exist in ql on line 16", messages.get(0).getBody());
    }

    @Test
    void multiplequestions() throws IOException {
        Form form = createForm("qls/tests/assets/validquestionnaire.ql");
        Stylesheet stylesheet = createStylesheet("qls/tests/assets/multiplequestions.qls");

        ql.semantic.Analyzer qlAnalyzer = new ql.semantic.Analyzer();
        ReferenceTable referenceTable = qlAnalyzer.analyze(form);

        List<Message> messages = qlAnalyzer.getMessages();

        qls.semantic.Analyzer analyzer = new qls.semantic.Analyzer(referenceTable, messages);

            analyzer.analyze(stylesheet);

        assertEquals(false, messages.isEmpty());
        assertEquals(1, messages.size());
        assertEquals("Error: [QLS] The question Name1 is already defined in the QLS on line 16", messages.get(0).getBody());
    }

    @Test
    void wrongtypes() throws IOException {
        Form form = createForm("qls/tests/assets/validquestionnaire.ql");
        Stylesheet stylesheet = createStylesheet("qls/tests/assets/wrongtypes.qls");

        ql.semantic.Analyzer qlAnalyzer = new ql.semantic.Analyzer();
        ReferenceTable referenceTable = qlAnalyzer.analyze(form);

        List<Message> messages = qlAnalyzer.getMessages();

        qls.semantic.Analyzer analyzer = new qls.semantic.Analyzer(referenceTable, messages);

        analyzer.analyze(stylesheet);

        assertEquals(false, messages.isEmpty());
        assertEquals(1, messages.size());
        assertEquals("Error: [QLS] The widget type boolean is not of the expected widget type: integer on line 2", messages.get(0).getBody());
    }

    private Form createForm(String file) throws IOException {
        InputStream src = new FileInputStream(file);
        ANTLRInputStream input = new ANTLRInputStream(src);

        QLLexer lexer = new QLLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        return parser.form().result;
    }

    private Stylesheet createStylesheet(String file) throws IOException {
        InputStream src = new FileInputStream(file);
        ANTLRInputStream input = new ANTLRInputStream(src);

        qlsLexer lexer = new qlsLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        qlsParser parser = new qlsParser(tokens);
        return parser.stylesheet().result;
    }
}