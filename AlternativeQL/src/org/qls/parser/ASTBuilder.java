package org.qls.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.ql.ast.Identifier;
import org.ql.ast.Node;
import org.ql.ast.SourceLocation;
import org.qls.ast.page.DefaultWidget;
import org.qls.ast.page.Page;
import org.qls.ast.page.Question;
import org.qls.ast.page.Section;
import org.qls.ast.StyleSheet;
import org.qls.grammar.QLSParser;
import org.qls.grammar.QLSVisitor;

import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends AbstractParseTreeVisitor<Node> implements QLSVisitor<Node> {
    @Override
    public Node visitStylesheet(QLSParser.StylesheetContext ctx) {
        List<Page> pages = new ArrayList<>();

        for (QLSParser.PageContext pageContext : ctx.page()) {
            pages.add((Page) visit(pageContext));
        }

        return new StyleSheet((Identifier) visit(ctx.identifier()), pages);
    }

    @Override
    public Node visitPage(QLSParser.PageContext ctx) {
        List<Section> sections = new ArrayList<>();
        List<DefaultWidget> defaultWidgets = new ArrayList<>();

        for (QLSParser.SectionContext sectionContext : ctx.section()) {
            sections.add((Section) visit(sectionContext));
        }

        for (QLSParser.DefaultWidgetContext defaultWidgetContext : ctx.defaultWidget()) {
            defaultWidgets.add((DefaultWidget) visit(defaultWidgetContext));
        }

        return new Page((Identifier) visit(ctx.identifier()), sections, defaultWidgets);
    }

    @Override
    public Node visitSection(QLSParser.SectionContext ctx) {
        List<Question> questions = new ArrayList<>();
        List<Section> sections = new ArrayList<>();
        List<DefaultWidget> defaultWidgets = new ArrayList<>();

        for(QLSParser.QuestionContext question : ctx.question()) {
            questions.add((Question) visit(question));
        }

        for(QLSParser.SectionContext section : ctx.section()) {
            sections.add((Section) visit(section));
        }

        for(QLSParser.DefaultWidgetContext defaultWidget : ctx.defaultWidget()) {
            defaultWidgets.add((DefaultWidget) visit(defaultWidget));
        }

        return new Section(ctx.name.getText(), questions, sections, defaultWidgets);
    }

    @Override
    public Node visitQuestionNoWidget(QLSParser.QuestionNoWidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitQuestionWidget(QLSParser.QuestionWidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitWidget(QLSParser.WidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitSpinboxWidget(QLSParser.SpinboxWidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitSliderWidget(QLSParser.SliderWidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitTextWidget(QLSParser.TextWidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitCheckboxWidget(QLSParser.CheckboxWidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitDropdownWidget(QLSParser.DropdownWidgetContext ctx) {
        return null;
    }

    @Override
    public Node visitDefaultNoStyle(QLSParser.DefaultNoStyleContext ctx) {
        return null;
    }

    @Override
    public Node visitDefaultWithStyle(QLSParser.DefaultWithStyleContext ctx) {
        return null;
    }

    @Override
    public Node visitWidthRule(QLSParser.WidthRuleContext ctx) {
        return null;
    }

    @Override
    public Node visitFontRule(QLSParser.FontRuleContext ctx) {
        return null;
    }

    @Override
    public Node visitFontSizeRule(QLSParser.FontSizeRuleContext ctx) {
        return null;
    }

    @Override
    public Node visitTypeBoolean(QLSParser.TypeBooleanContext ctx) {
        return null;
    }

    @Override
    public Node visitTypeFloat(QLSParser.TypeFloatContext ctx) {
        return null;
    }

    @Override
    public Node visitTypeInteger(QLSParser.TypeIntegerContext ctx) {
        return null;
    }

    @Override
    public Node visitTypeString(QLSParser.TypeStringContext ctx) {
        return null;
    }

    @Override
    public Node visitTypeMoney(QLSParser.TypeMoneyContext ctx) {
        return null;
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
}
