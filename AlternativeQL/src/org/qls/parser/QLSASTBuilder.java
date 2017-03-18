package org.qls.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.ql.ast.Identifier;
import org.ql.ast.Node;
import org.ql.ast.SourceLocation;
import org.ql.ast.type.*;
import org.qls.ast.page.*;
import org.qls.ast.StyleSheet;
import org.qls.ast.widget.*;
import org.qls.ast.widget.defaultWidget.DefaultWidget;
import org.qls.ast.widget.defaultWidget.DefaultWidgetNoStyle;
import org.qls.ast.widget.defaultWidget.DefaultWidgetWithStyle;
import org.qls.ast.widget.defaultWidget.style.FontRule;
import org.qls.ast.widget.defaultWidget.style.FontSizeRule;
import org.qls.ast.widget.defaultWidget.style.StyleRule;
import org.qls.ast.widget.defaultWidget.style.WidthRule;
import org.qls.grammar.QLSLexer;
import org.qls.grammar.QLSParser;
import org.qls.grammar.QLSVisitor;

import static org.util.ast.SourceLocationHydrator.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QLSASTBuilder extends AbstractParseTreeVisitor<Node> implements QLSVisitor<Node> {
    public StyleSheet buildAST(InputStream inputStream) throws IOException {
        return (StyleSheet) visitStylesheet(createParser(inputStream).stylesheet());
    }

    private QLSParser createParser(InputStream inputStream) throws IOException {
        return new QLSParser(new CommonTokenStream(new QLSLexer(new ANTLRInputStream(inputStream))));
    }

    @Override
    public Node visitStylesheet(QLSParser.StylesheetContext ctx) {
        List<Page> pages = new ArrayList<>();

        for (QLSParser.PageContext pageContext : ctx.page()) {
            pages.add((Page) visit(pageContext));
        }

        return hydrateSourceLocation(new StyleSheet((Identifier) visit(ctx.identifier()), pages), ctx);
    }

    @Override
    public Node visitPage(QLSParser.PageContext ctx) {
        List<PageItem> pageItems = visitPageItems(ctx);
        Identifier identifier = (Identifier) visit(ctx.identifier());

        return new Page(identifier, pageItems);
    }

    private List<PageItem> visitPageItems(QLSParser.PageContext ctx) {
        List<PageItem> pageItems = new ArrayList<>();
        for (QLSParser.PageItemContext pageItem : ctx.pageItem()) {
            pageItems.add((PageItem) visit(pageItem));
        }
        return pageItems;
    }

    @Override
    public Node visitPageSection(QLSParser.PageSectionContext ctx) {
        return visit(ctx.section());
    }

    @Override
    public Node visitPageDefaultWidget(QLSParser.PageDefaultWidgetContext ctx) {
        return visit(ctx.defaultWidget());
    }

    @Override
    public Node visitSection(QLSParser.SectionContext ctx) {
        List<Section> sections = getSections(ctx.section());
        List<DefaultWidget> defaultWidgets = getDefaultWidgets(ctx.defaultWidget());
        List<Question> questions = getQuestions(ctx.question());

        return new Section(ctx.name.getText(), questions, sections, defaultWidgets);
    }

    @Override
    public Node visitSectionQuestion(QLSParser.SectionQuestionContext ctx) {
        return null;
    }

    @Override
    public Node visitSectionNested(QLSParser.SectionNestedContext ctx) {
        return null;
    }

    @Override
    public Node visitSectionDefaultWidget(QLSParser.SectionDefaultWidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitQuestionNoWidget(QLSParser.QuestionNoWidgetContext ctx) {
        return new Question((Identifier) visit(ctx.identifier()), null);
    }

    @Override
    public Node visitQuestionWidget(QLSParser.QuestionWidgetContext ctx) {
        return new Question((Identifier) visit(ctx.identifier()), (Widget) visit(ctx.widget()));
    }

    @Override
    public Node visitWidget(QLSParser.WidgetContext ctx) {
        return visit(ctx.widgetType());
    }

    @Override
    public Node visitSpinboxWidget(QLSParser.SpinboxWidgetContext ctx) {
        return new SpinboxWidget();
    }

    @Override
    public Node visitSliderWidget(QLSParser.SliderWidgetContext ctx) {
        return new SliderWidget();
    }

    @Override
    public Node visitTextWidget(QLSParser.TextWidgetContext ctx) {
        return new TextWidget();
    }

    @Override
    public Node visitCheckboxWidget(QLSParser.CheckboxWidgetContext ctx) {
        return new CheckboxWidget();
    }

    @Override
    public Node visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        return new RadioWidget(ctx.yes.getText(), ctx.no.getText());
    }

    @Override
    public Node visitDropdownWidget(QLSParser.DropdownWidgetContext ctx) {
        return new DropdownWidget(ctx.yes.getText(), ctx.no.getText());
    }

    @Override
    public Node visitDefaultNoStyle(QLSParser.DefaultNoStyleContext ctx) {
        return new DefaultWidgetNoStyle((Type) visit(ctx.type()), (Widget) visit(ctx.widget()));
    }

    @Override
    public Node visitDefaultWithStyle(QLSParser.DefaultWithStyleContext ctx) {
        List<StyleRule> styleRules = new ArrayList<>();

        for(QLSParser.StyleRuleContext styleRuleContext : ctx.styleRule()) {
            styleRules.add((StyleRule) visit(styleRuleContext));
        }

        return new DefaultWidgetWithStyle((Type) visit(ctx.type()), (Widget) visit(ctx.widget()), styleRules);
    }

    @Override
    public Node visitWidthRule(QLSParser.WidthRuleContext ctx) {
        return new WidthRule(Integer.parseInt(ctx.INTEGER_LITERAL().getSymbol().getText()));
    }

    @Override
    public Node visitFontRule(QLSParser.FontRuleContext ctx) {
        return new FontRule(ctx.STRING_LITERAL().getText());
    }

    @Override
    public Node visitFontSizeRule(QLSParser.FontSizeRuleContext ctx) {
        return new FontSizeRule(Integer.parseInt(ctx.INTEGER_LITERAL().getSymbol().getText()));
    }

    @Override
    public Node visitTypeBoolean(QLSParser.TypeBooleanContext ctx) {
        BooleanType booleanType = new BooleanType();
        booleanType.setSourceLocation(extractSourceLocation(ctx));

        return booleanType;
    }

    @Override
    public Node visitTypeFloat(QLSParser.TypeFloatContext ctx) {
        FloatType floatType = new FloatType();
        floatType.setSourceLocation(extractSourceLocation(ctx));

        return floatType;
    }

    @Override
    public Node visitTypeInteger(QLSParser.TypeIntegerContext ctx) {
        IntegerType integerType = new IntegerType();
        integerType.setSourceLocation(extractSourceLocation(ctx));

        return integerType;
    }

    @Override
    public Node visitTypeString(QLSParser.TypeStringContext ctx) {
        StringType stringType = new StringType();
        stringType.setSourceLocation(extractSourceLocation(ctx));

        return stringType;
    }

    @Override
    public Node visitTypeMoney(QLSParser.TypeMoneyContext ctx) {
        MoneyType moneyType = new MoneyType();
        moneyType.setSourceLocation(extractSourceLocation(ctx));

        return moneyType;
    }

    @Override
    public Node visitIdentifier(QLSParser.IdentifierContext ctx) {
        Identifier identifier = new Identifier(ctx.ID().getText());
        identifier.setSourceLocation(extractSourceLocation(ctx));

        return identifier;
    }

    private SourceLocation extractSourceLocation(ParserRuleContext context) {
        return new SourceLocation(context.start.getLine(), context.start.getCharPositionInLine());
    }

    private List<DefaultWidget> getDefaultWidgets(List<QLSParser.DefaultWidgetContext> defaultWidgetContexts) {
        List<DefaultWidget> defaultWidgets = new ArrayList<>();

        for(QLSParser.DefaultWidgetContext defaultWidget : defaultWidgetContexts) {
            defaultWidgets.add((DefaultWidget) visit(defaultWidget));
        }

        return defaultWidgets;
    }

    private List<Section> getSections(List<QLSParser.SectionContext> sectionContexts) {
        List<Section> sections = new ArrayList<>();

        for (QLSParser.SectionContext section : sectionContexts) {
            sections.add((Section) visit(section));
        }

        return sections;
    }

    private List<Question> getQuestions(List<QLSParser.QuestionContext> questionContexts) {
        List<Question> questions = new ArrayList<>();

        for (QLSParser.QuestionContext question : questionContexts) {
            questions.add((Question) visit(question));
        }

        return questions;
    }
}
