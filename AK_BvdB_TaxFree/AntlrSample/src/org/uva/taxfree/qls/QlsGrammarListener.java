package org.uva.taxfree.qls;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.taxfree.ql.gen.QLSGrammarBaseListener;
import org.uva.taxfree.ql.gen.QLSGrammarParser;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.qls.styleoption.*;
import org.uva.taxfree.qls.styleoption.widget.SliderWidget;
import org.uva.taxfree.qls.styleoption.widget.SpinboxWidget;

import java.util.ArrayList;
import java.util.List;

public class QlsGrammarListener extends QLSGrammarBaseListener {

    private final List<Page> mPages;
    private final List<Section> mCachedSections;
    private final List<QuestionStyle> mCachedQuestionStyles;
    private final List<StyleOption> mCachedStyleOptions;
    private StyleOption mCachedWidgetStyleOption;

    public QlsGrammarListener() {
        mPages = new ArrayList<>();
        mCachedSections = new ArrayList<>();
        mCachedQuestionStyles = new ArrayList<>();
        mCachedStyleOptions = new ArrayList<>();
    }

    private void addStyleOption(StyleOption styleOption) {
        mCachedStyleOptions.add(styleOption);
    }

    private List<StyleOption> popCachedStyles() {
        List<StyleOption> cachedStyles = new ArrayList<>(mCachedStyleOptions);
        mCachedStyleOptions.clear();
        return cachedStyles;
    }

    private List<QuestionStyle> popCachedQuestionStyles() {
        List<QuestionStyle> cachedQuestionStyles = new ArrayList<>(mCachedQuestionStyles);
        mCachedQuestionStyles.clear();
        return cachedQuestionStyles;
    }

    private List<Section> popCachedSections() {
        List<Section> cachedSections = new ArrayList<>(mCachedSections);
        mCachedSections.clear();
        return cachedSections;
    }

    private SourceInfo createSourceInfo(ParserRuleContext context) {
        // TODO: Remove duplication...
        int startLineNumber = context.getStart().getLine();
        int startColumn = context.getStart().getCharPositionInLine();
        int endLineNumber = context.getStop().getLine();
        int endColumn = calcEndColumn(startLineNumber, startColumn, endLineNumber, context);
        return new SourceInfo(startLineNumber, startColumn, endLineNumber, endColumn);
    }

    private int calcEndColumn(int startLineNumber, int endLineNumber, int startColumn, ParserRuleContext context) {
        int endColumn = context.getStop().getCharPositionInLine();
        if (startLineNumber == endLineNumber && startColumn == endColumn) {
            // Literals have an invalid endColumn, so we need to calculate it by ourselves
            endColumn = startColumn + context.getText().length();
        }
        return endColumn;
    }

    // Enters
    @Override
    public void enterSpinboxWidget(QLSGrammarParser.SpinboxWidgetContext ctx) {
        super.enterSpinboxWidget(ctx);
        mCachedWidgetStyleOption = new SpinboxWidget(createSourceInfo(ctx));
    }

    @Override
    public void enterSliderWidget(QLSGrammarParser.SliderWidgetContext ctx) {
        super.enterSliderWidget(ctx);
        mCachedWidgetStyleOption = new SliderWidget(createSourceInfo(ctx));
    }

    @Override
    public void enterFontStyle(QLSGrammarParser.FontStyleContext ctx) {
        super.enterFontStyle(ctx);
        addStyleOption(new FontStyleOption(ctx.STRING_LITERAL().getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterFontsizeStyle(QLSGrammarParser.FontsizeStyleContext ctx) {
        super.enterFontsizeStyle(ctx);
        addStyleOption(new FontSizeStyleOption(Integer.valueOf(ctx.INTEGER_LITERAL().getText()), createSourceInfo(ctx)));
    }

    @Override
    public void enterColorStyle(QLSGrammarParser.ColorStyleContext ctx) {
        super.enterColorStyle(ctx);
        addStyleOption(new ColorStyleOption(ctx.COLOR_LITERAL().getText(), createSourceInfo(ctx)));
    }

    @Override
    public void enterBackgroundColorStyle(QLSGrammarParser.BackgroundColorStyleContext ctx) {
        super.enterBackgroundColorStyle(ctx);
        addStyleOption(new BackgroundColorStyleOption(ctx.COLOR_LITERAL().getText(), createSourceInfo(ctx)));
    }

    // Exits
    @Override
    public void exitWidgetStyle(QLSGrammarParser.WidgetStyleContext ctx) {
        super.exitWidgetStyle(ctx);
        addStyleOption(mCachedWidgetStyleOption);
    }

    @Override
    public void exitQuestion(QLSGrammarParser.QuestionContext ctx) {
        super.exitQuestion(ctx);
        QuestionStyle questionStyle = new QuestionStyle(ctx.VARIABLE_LITERAL().getText(), popCachedStyles(), createSourceInfo(ctx));
        mCachedQuestionStyles.add(questionStyle);
    }

    @Override
    public void exitSection(QLSGrammarParser.SectionContext ctx) {
        super.exitSection(ctx);
        Section section = new Section(ctx.STRING_LITERAL().getText(), popCachedQuestionStyles(), createSourceInfo(ctx));
        mCachedSections.add(section);
    }

    @Override
    public void exitPage(QLSGrammarParser.PageContext ctx) {
        super.exitPage(ctx);
        Page page = new Page(ctx.VARIABLE_LITERAL().getText(), popCachedSections(), createSourceInfo(ctx));
        mPages.add(page);
    }
}
