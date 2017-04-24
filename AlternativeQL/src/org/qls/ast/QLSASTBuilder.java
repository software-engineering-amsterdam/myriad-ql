package org.qls.ast;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.Node;
import org.ql.ast.type.*;
import org.qls.ast.page.*;
import org.qls.ast.widget.*;
import org.qls.ast.widget.default_widget.DefaultWidget;
import org.qls.ast.widget.default_widget.DefaultWidgetNoStyle;
import org.qls.ast.widget.default_widget.DefaultWidgetSet;
import org.qls.ast.widget.default_widget.DefaultWidgetWithStyle;
import org.qls.ast.widget.default_widget.style.*;
import org.qls.grammar.QLSLexer;
import org.qls.grammar.QLSParser;
import org.qls.grammar.QLSVisitor;

import static org.ql.ast.SourceLocationHydrator.hydrateSourceLocation;
import static org.ql.ast.StringUnquoter.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QLSASTBuilder extends AbstractParseTreeVisitor<Node> implements QLSVisitor<Node> {
    public StyleSheet buildAST(InputStream inputStream) throws IOException {
        return visitStylesheet(createParser(inputStream).stylesheet());
    }

    private QLSParser createParser(InputStream inputStream) throws IOException {
        return new QLSParser(new CommonTokenStream(new QLSLexer(new ANTLRInputStream(inputStream))));
    }

    @Override
    public StyleSheet visitStylesheet(QLSParser.StylesheetContext ctx) {
        List<Page> pages = new ArrayList<>();

        for (QLSParser.PageContext pageContext : ctx.page()) {
            pages.add((Page) visit(pageContext));
        }

        return hydrateSourceLocation(new StyleSheet(visitIdentifier(ctx.identifier()), pages), ctx);
    }

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        List<Section> sections = getSections(ctx.section());
        DefaultWidgetSet defaultWidgets = getDefaultWidgets(ctx.defaultWidget());

        return hydrateSourceLocation(new Page(visitIdentifier(ctx.identifier()), sections, defaultWidgets), ctx);
    }

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        List<Section> sections = getSections(ctx.section());
        DefaultWidgetSet defaultWidgets = getDefaultWidgets(ctx.defaultWidget());
        List<WidgetQuestion> questions = getQuestions(ctx.question());

        return hydrateSourceLocation(new Section(unquoteString(ctx.name.getText()), questions, sections, defaultWidgets), ctx);
    }

    @Override
    public WidgetQuestion visitQuestionNoWidget(QLSParser.QuestionNoWidgetContext ctx) {
        return hydrateSourceLocation(new WidgetQuestion(visitIdentifier(ctx.identifier())), ctx);
    }

    @Override
    public CustomWidgetQuestion visitQuestionWidget(QLSParser.QuestionWidgetContext ctx) {
        return hydrateSourceLocation(new CustomWidgetQuestion(visitIdentifier(ctx.identifier()), (Widget) visit(ctx.widget())), ctx);
    }

    @Override
    public Widget visitWidget(QLSParser.WidgetContext ctx) {
        return (Widget) visit(ctx.widgetType());
    }

    @Override
    public Spinbox visitSpinbox(QLSParser.SpinboxContext ctx) {
        return hydrateSourceLocation(new Spinbox(), ctx);
    }

    @Override
    public Slider visitSlider(QLSParser.SliderContext ctx) {
        return hydrateSourceLocation(new Slider(), ctx);
    }

    @Override
    public Text visitText(QLSParser.TextContext ctx) {
        return hydrateSourceLocation(new Text(), ctx);
    }

    @Override
    public Checkbox visitCheckbox(QLSParser.CheckboxContext ctx) {
        return hydrateSourceLocation(new Checkbox(), ctx);
    }

    @Override
    public Radio visitRadio(QLSParser.RadioContext ctx) {
        Radio radio = new Radio(unquoteString(ctx.yes.getText()), unquoteString(ctx.no.getText()));
        return hydrateSourceLocation(radio, ctx);
    }

    @Override
    public Dropdown visitDropdown(QLSParser.DropdownContext ctx) {
        Dropdown dropdown = new Dropdown(unquoteString(ctx.yes.getText()), unquoteString(ctx.no.getText()));
        return hydrateSourceLocation(dropdown, ctx);
    }

    @Override
    public DefaultWidgetNoStyle visitDefaultNoStyle(QLSParser.DefaultNoStyleContext ctx) {
        return hydrateSourceLocation(new DefaultWidgetNoStyle((Type) visit(ctx.type()), (Widget) visit(ctx.widget())), ctx);
    }

    @Override
    public DefaultWidgetWithStyle visitDefaultWithStyle(QLSParser.DefaultWithStyleContext ctx) {
        Set<StyleRule> styleRules = new HashSet<>();

        for (QLSParser.StyleRuleContext styleRuleContext : ctx.styleRule()) {
            styleRules.add((StyleRule) visit(styleRuleContext));
        }

        return hydrateSourceLocation(new DefaultWidgetWithStyle((Type) visit(ctx.type()), (Widget) visit(ctx.widget()), styleRules), ctx);
    }

    @Override
    public WidthRule visitWidthRule(QLSParser.WidthRuleContext ctx) {
        return hydrateSourceLocation(new WidthRule(Integer.parseInt(ctx.INTEGER_LITERAL().getSymbol().getText())), ctx);
    }

    @Override
    public FontRule visitFontRule(QLSParser.FontRuleContext ctx) {
        return hydrateSourceLocation(new FontRule(unquoteString(ctx.STRING_LITERAL().getText())), ctx);
    }

    @Override
    public FontSizeRule visitFontSizeRule(QLSParser.FontSizeRuleContext ctx) {
        return hydrateSourceLocation(new FontSizeRule(Integer.parseInt(ctx.INTEGER_LITERAL().getSymbol().getText())), ctx);
    }

    @Override
    public ColorRule visitColorRule(QLSParser.ColorRuleContext ctx) {
        return new ColorRule(ctx.COLOR_LITERAL().getText());
    }

    @Override
    public BooleanType visitTypeBoolean(QLSParser.TypeBooleanContext ctx) {
        return hydrateSourceLocation(new BooleanType(), ctx);
    }

    @Override
    public FloatType visitTypeFloat(QLSParser.TypeFloatContext ctx) {
        return hydrateSourceLocation(new FloatType(), ctx);
    }

    @Override
    public IntegerType visitTypeInteger(QLSParser.TypeIntegerContext ctx) {
        return hydrateSourceLocation(new IntegerType(), ctx);
    }

    @Override
    public StringType visitTypeString(QLSParser.TypeStringContext ctx) {
        return hydrateSourceLocation(new StringType(), ctx);
    }

    @Override
    public MoneyType visitTypeMoney(QLSParser.TypeMoneyContext ctx) {
        return hydrateSourceLocation(new MoneyType(), ctx);
    }

    @Override
    public Identifier visitIdentifier(QLSParser.IdentifierContext ctx) {
        return hydrateSourceLocation(new Identifier(ctx.ID().getText()), ctx);
    }

    private DefaultWidgetSet getDefaultWidgets(List<QLSParser.DefaultWidgetContext> defaultWidgetContexts) {
        DefaultWidgetSet defaultWidgets = new DefaultWidgetSet();

        for (QLSParser.DefaultWidgetContext defaultWidget : defaultWidgetContexts) {
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

    private List<WidgetQuestion> getQuestions(List<QLSParser.QuestionContext> questionContexts) {
        List<WidgetQuestion> questions = new ArrayList<>();

        for (QLSParser.QuestionContext question : questionContexts) {
            questions.add((WidgetQuestion) visit(question));
        }

        return questions;
    }
}
