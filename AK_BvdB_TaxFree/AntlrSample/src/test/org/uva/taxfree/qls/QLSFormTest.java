package test.org.uva.taxfree.qls;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.ast.QlAstBuilder;
import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.blocks.FormNode;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.util.FileUtility;
import org.uva.taxfree.qls.*;
import org.uva.taxfree.qls.styleoption.StyleOption;
import org.uva.taxfree.qls.styleoption.widget.CheckboxWidget;
import test.org.uva.taxfree.ql.SemanticsTester;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QLSFormTest extends SemanticsTester {
    QlsStyleBuilder mStyleBuilder;
    QlsStyle mQlsStyle;

    public static void main(String args[]) {
        QLSFormTest main = new QLSFormTest();
        try {
            main.executeMain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeMain() throws IOException {
        // Generate AST
        QlAstBuilder builder = new QlAstBuilder(testFile("SimpleForm.txfrm"));
        MessageList messageList = new MessageList();
        FormNode ast = builder.generateAst(messageList);
        SymbolTable symbolTable = new SymbolTable();
        ast.fillSymbolTable(symbolTable);
        createQls("SimpleForm.qls", messageList);
        QuestionForm taxForm = new QlsForm(ast.toString(), symbolTable, mQlsStyle);
        ast.fillQuestionForm(taxForm);
        taxForm.show();
    }

    @Test
    public void TestPages() {
        Page p = generatePage();
        Assert.assertTrue(p.contains("likesToPayTaxes"), "Page contains the question");
        Assert.assertFalse(p.contains("someUnknownQuestion"), "Page does not contain this question");
    }

    private Page generatePage() {
        return new Page("Taxes", generateSections(), generateDefaultStyles(), generateSourceInfo());
    }

    private List<Section> generateSections() {
        List<Section> sections = new ArrayList<>();
        sections.add(new Section("Meta", generateQuestionStyles(), generateSourceInfo()));
        return sections;
    }

    private List<DefaultStyle> generateDefaultStyles() {
        List<DefaultStyle> defaultStyles = new ArrayList<>();
        defaultStyles.add(new DefaultStyle(new BooleanType(), generateStyleOptions(), generateSourceInfo()));
        return defaultStyles;
    }

    private List<QuestionStyle> generateQuestionStyles() {
        List<QuestionStyle> questionStyles = new ArrayList<>();
        questionStyles.add(new QuestionStyle("likesToPayTaxes", generateStyleOptions(), generateSourceInfo()));
        return questionStyles;
    }

    private SourceInfo generateSourceInfo() {
        return new SourceInfo(0, 0, 0, 0);
    }

    private List<StyleOption> generateStyleOptions() {
        List<StyleOption> styleOptions = new ArrayList<>();
        styleOptions.add(new CheckboxWidget(generateSourceInfo()));
        return styleOptions;
    }

    @Test
    public void testSemantics_simpleForm() throws Exception {
        assertSemantics("SimpleForm.txfrm", 0, "Qls should not contain errors");
    }

    @Test
    public void testSemantics_oneQuestionUnstyled() throws Exception {
        assertSemantics("OneQuestionUnstyled.txfrm", 0, "Qls should not contain errors");
    }

    @Test
    public void testSemantics_oneQuestionWidgetStyle() throws Exception {
        assertSemantics("OneQuestionWidgetStyle.txfrm", 0, "Qls should not contain errors");
    }

    @Test
    public void testSemantics_oneQuestionStyleOptions() throws Exception {
        assertSemantics("OneQuestionStyleOptions.txfrm", 0, "Qls should not contain errors");
    }

    @Test
    public void testSemantics_oneQuestionDefaultStyle() throws Exception {
        assertSemantics("OneQuestionDefaultStyle.txfrm", 0, "Qls should not contain errors");
    }

    @Test
    public void testSemantics_duplicateAssignment() throws Exception {
        assertSemantics("DuplicateAssignment.txfrm", 3, "Assigning questions multiple times yields errors");
    }

    @Test
    public void testSemantics_invalidAssignment() throws Exception {
        assertSemantics("InvalidWidgetStyle.txfrm", 1, "Assigning invalid widgets yields errors");
    }


    private void createQls(String taxFile, MessageList messageList) {
        String styleFile = FileUtility.replaceExtension(taxFile, ".qls");
        File style = testFile(styleFile);
        Assert.assertTrue(style.exists(), "File not found: " + styleFile);
        mStyleBuilder = new QlsStyleBuilder(style);
        mQlsStyle = mStyleBuilder.generateStyle(messageList);
    }

    @Override
    protected void assertSemantics(String fileName, int expectedErrorAmount, String description) throws IOException {
        super.assertSemantics(fileName, 0, "only works for valid tax forms");
        MessageList messages = new MessageList();
        createQls(fileName, messages);
        mQlsStyle.checkSemantics(getSymbolTable(), messages);
        System.out.println(messages);
        Assert.assertEquals(expectedErrorAmount, messages.messageAmount(), "invalid error amount detected: " + description);
    }

    @Override
    protected File testFile(String fileName) {
        return new File("src\\test\\org\\uva\\taxfree\\qls\\" + fileDirectory() + "\\" + fileName);
    }

    @Override
    protected String fileDirectory() {
        return "testFiles";
    }
}
