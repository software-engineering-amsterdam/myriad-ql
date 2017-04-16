package org.uva.taxfree.qls;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.taxfree.ql.ast.AntlrBuilder;
import org.uva.taxfree.ql.gen.QLSGrammarBaseListener;
import org.uva.taxfree.ql.gen.QLSGrammarLexer;
import org.uva.taxfree.ql.gen.QLSGrammarParser;
import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.IntegerType;
import org.uva.taxfree.ql.model.types.StringType;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.qls.styleoption.*;
import org.uva.taxfree.qls.styleoption.widget.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QlsStyleBuilder extends QLSGrammarBaseListener {
    private final AntlrBuilder mBuilder;

    private QlsStyle mQlsStyle;
    private final List<Page> mCachedPages;
    private final List<Section> mCachedSections;
    private final List<QuestionStyle> mCachedQuestionStyles;
    private final List<StyleOption> mCachedStyleOptions;
    private final List<DefaultStyle> mCachedDefaultStyles;

    public QlsStyleBuilder(File inputFile) {
        mBuilder = new AntlrBuilder(inputFile);
        mCachedPages = new ArrayList<>();
        mCachedSections = new ArrayList<>();
        mCachedQuestionStyles = new ArrayList<>();
        mCachedStyleOptions = new ArrayList<>();
        mCachedDefaultStyles = new ArrayList<>();

    }

    private void cacheStyleOption(StyleOption styleOption) {
        mCachedStyleOptions.add(styleOption);
    }

    private void cacheWidgetStyle(WidgetStyleOption widgetStyleOption) {
        cacheStyleOption(widgetStyleOption);
    }

    private List<StyleOption> popCachedStyleOptions() {
        List<StyleOption> cachedList = new ArrayList<>(mCachedStyleOptions);
        mCachedStyleOptions.clear();
        return cachedList;
    }

    private List<DefaultStyle> popCachedDefaultStyles() {
        List<DefaultStyle> cachedList = new ArrayList<>(mCachedDefaultStyles);
        mCachedDefaultStyles.clear();
        return cachedList;
    }

    private List<QuestionStyle> popCachedQuestionStyles() {
        List<QuestionStyle> cachedList = new ArrayList<>(mCachedQuestionStyles);
        mCachedQuestionStyles.clear();
        return cachedList;
    }

    private List<Section> popCachedSections() {
        List<Section> cachedList = new ArrayList<>(mCachedSections);
        mCachedSections.clear();
        return cachedList;
    }

    public QlsStyle generateStyle(MessageList messageList) {
        try {
            QLSGrammarParser parser;
            try {
                parser = createGrammarParser();
            } catch (IOException e) {
                messageList.addError("(QlsStyleBuilder.java:80): Unable to create grammarParser: " + e.getMessage());
                return null;
            }

            parser.addErrorListener(mBuilder.createErrorListener());
            mBuilder.walkParseTree(this, parser.stylesheet());
            return mQlsStyle;
        } catch (UnsupportedOperationException e) {
            messageList.addError("(QlsStyleBuilder.java:88): Couldn't generate style because of a parse error: " + e.getMessage());
            return null;
        }
    }

    private QLSGrammarParser createGrammarParser() throws IOException {
        QLSGrammarLexer qlsGrammarLexer = new QLSGrammarLexer(mBuilder.createInputStream());
        return new QLSGrammarParser(mBuilder.createTokenStream(qlsGrammarLexer));
    }

    // Enters
    // Widgets
    @Override
    public void enterCheckboxWidget(QLSGrammarParser.CheckboxWidgetContext ctx) {
        super.enterCheckboxWidget(ctx);
        cacheWidgetStyle(new CheckboxWidget(createSourceInfo(ctx)));
    }

    @Override
    public void enterDropdownWidget(QLSGrammarParser.DropdownWidgetContext ctx) {
        super.enterDropdownWidget(ctx);
        cacheWidgetStyle(new DropdownWidget(ctx.textTrue.getText(), ctx.textFalse.getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterRadioWidget(QLSGrammarParser.RadioWidgetContext ctx) {
        super.enterRadioWidget(ctx);
        cacheWidgetStyle(new RadioWidget(ctx.textTrue.getText(), ctx.textFalse.getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterSliderWidget(QLSGrammarParser.SliderWidgetContext ctx) {
        super.enterSliderWidget(ctx);
        cacheWidgetStyle(new SliderWidget(createSourceInfo(ctx)));
    }

    @Override
    public void enterSpinboxWidget(QLSGrammarParser.SpinboxWidgetContext ctx) {
        super.enterSpinboxWidget(ctx);
        cacheWidgetStyle(new SpinboxWidget(createSourceInfo(ctx)));
    }

    @Override
    public void enterTextWidget(QLSGrammarParser.TextWidgetContext ctx) {
        super.enterTextWidget(ctx);
        cacheWidgetStyle(new TextWidget(createSourceInfo(ctx)));
    }

    // Properties
    @Override
    public void enterFontStyle(QLSGrammarParser.FontStyleContext ctx) {
        super.enterFontStyle(ctx);
        cacheStyleOption(new FontStyleOption(ctx.STRING_LITERAL().getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterFontSizeStyle(QLSGrammarParser.FontSizeStyleContext ctx) {
        super.enterFontSizeStyle(ctx);
        cacheStyleOption(new FontSizeStyleOption(Integer.valueOf(ctx.INTEGER_LITERAL().getText()), createSourceInfo(ctx)));
    }

    @Override
    public void enterColorStyle(QLSGrammarParser.ColorStyleContext ctx) {
        super.enterColorStyle(ctx);
        cacheStyleOption(new ColorStyleOption(ctx.COLOR_LITERAL().getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterBackgroundColorStyle(QLSGrammarParser.BackgroundColorStyleContext ctx) {
        super.enterBackgroundColorStyle(ctx);
        cacheStyleOption(new BackgroundColorStyleOption(ctx.COLOR_LITERAL().getText(), createSourceInfo(ctx)));
    }

    @Override
    public void exitQuestion(QLSGrammarParser.QuestionContext ctx) {
        super.exitQuestion(ctx);
        // A question without style options
        mCachedQuestionStyles.add(new QuestionStyle(ctx.VARIABLE_LITERAL().getText(), new ArrayList<>(), createSourceInfo(ctx)));
    }

    // Exits
    @Override
    public void exitQuestionWithWidget(QLSGrammarParser.QuestionWithWidgetContext ctx) {
        super.exitQuestionWithWidget(ctx);
        mCachedQuestionStyles.add(new QuestionStyle(ctx.VARIABLE_LITERAL().getText(), popCachedStyleOptions(), createSourceInfo(ctx)));
    }

    @Override
    public void exitQuestionWithStyle(QLSGrammarParser.QuestionWithStyleContext ctx) {
        super.exitQuestionWithStyle(ctx);
        mCachedQuestionStyles.add(new QuestionStyle(ctx.VARIABLE_LITERAL().getText(), popCachedStyleOptions(), createSourceInfo(ctx)));
    }

    @Override
    public void exitDefaultStyle(QLSGrammarParser.DefaultStyleContext ctx) {
        super.exitDefaultStyle(ctx);
        String varTypeText = ctx.varType().getText();
        Type type;
        if ("boolean".equals(varTypeText)) {
            type = new BooleanType();
        } else if ("string".equals(varTypeText)) {
            type = new StringType();
        } else if ("integer".equals(varTypeText)) {
            type = new IntegerType();
        } else {
            throw new TypeNotPresentException(varTypeText, new Throwable("This is an unsupported type for QLS!"));
        }
        mCachedDefaultStyles.add(new DefaultStyle(type, popCachedStyleOptions(), createSourceInfo(ctx)));
    }

    @Override
    public void exitSection(QLSGrammarParser.SectionContext ctx) {
        super.exitSection(ctx);
        mCachedSections.add(new Section(ctx.sectionName.getText(), popCachedQuestionStyles(), createSourceInfo(ctx)));
    }

    @Override
    public void exitPage(QLSGrammarParser.PageContext ctx) {
        super.exitPage(ctx);
        mCachedPages.add(new Page(ctx.VARIABLE_LITERAL().getText(), popCachedSections(), popCachedDefaultStyles(), createSourceInfo(ctx)));
    }

    @Override
    public void exitStylesheet(QLSGrammarParser.StylesheetContext ctx) {
        super.exitStylesheet(ctx);
        mQlsStyle = new QlsStyle(mCachedPages);
    }

    private SourceInfo createSourceInfo(ParserRuleContext context) {
        return mBuilder.createSourceInfo(context);
    }
}
