/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/QLSASTVisitor.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes;

import org.antlr.v4.runtime.ParserRuleContext;
import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import ql.astnodes.types.*;
import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.astnodes.sections.DefaultStyle;
import qls.astnodes.sections.Section;
import qls.astnodes.sections.StyleQuestion;
import qls.astnodes.styles.*;
import qls.astnodes.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class QLSASTVisitor extends QLSBaseVisitor<Node> {

    @Override
    public Node visitStylesheet(QLSParser.StylesheetContext ctx) {
        String name = ctx.Identifier().getText();
        List<Section> sections = new ArrayList<>();

        for (QLSParser.SectionContext sectionContext : ctx.section()) {
            Section section = (Section) sectionContext.accept(this);
            sections.add(section);
        }

        List<DefaultStyle> defaultStyles = new ArrayList<>();

        for (QLSParser.DefaultStyleContext defaultStyleContext : ctx.defaultStyle()) {
            DefaultStyle defaultStyle = (DefaultStyle) defaultStyleContext.accept(this);
            defaultStyles.add(defaultStyle);
        }

        return new StyleSheet(name, sections, defaultStyles, getLineNumber(ctx));
    }
    

    @Override
    public Node visitSection(QLSParser.SectionContext ctx) {
        String name = ctx.STRING().getText();
        List<Section> sections = new ArrayList<>();

        for (QLSParser.SectionContext sectionContext : ctx.section()) {
            Section section = (Section) sectionContext.accept(this);
            sections.add(section);
        }

        List<DefaultStyle> defaultStyles = new ArrayList<>();

        for (QLSParser.DefaultStyleContext defaultStyleContext : ctx.defaultStyle()) {
            DefaultStyle defaultStyle = (DefaultStyle) defaultStyleContext.accept(this);
            defaultStyles.add(defaultStyle);
        }

        List<StyleQuestion> questions = new ArrayList<>();

        for (QLSParser.QuestionContext questionContext : ctx.question()) {
            StyleQuestion question = (StyleQuestion) questionContext.accept(this);
            questions.add(question);
        }

        return new Section(name, sections, defaultStyles, questions, getLineNumber(ctx));
    }

    @Override
    public Node visitWidgetQuestion(QLSParser.WidgetQuestionContext ctx) {
        String identifier = ctx.Identifier().getText();
        QLSWidget widget = (QLSWidget) ctx.widget().accept(this);
        widget.setLabel(identifier);
        return new StyleQuestion(identifier, widget, getLineNumber(ctx));
    }

    @Override
    public Node visitNormalQuestion(QLSParser.NormalQuestionContext ctx) {
        String identifier = ctx.Identifier().getText();
        return new StyleQuestion(identifier, new QLSUndefinedWidget(getLineNumber(ctx)), getLineNumber(ctx));
    }

    @Override
    public Node visitDefaultWithoutStyleDeclaration(QLSParser.DefaultWithoutStyleDeclarationContext ctx) {
        Type questionType = (Type) ctx.type().accept(this);
        QLSWidget widget = (QLSWidget) ctx.widget().accept(this);
        return new DefaultStyle(questionType, new UndefinedStyle(getLineNumber(ctx)), widget, getLineNumber(ctx));
    }

    @Override
    public Node visitDefaultWithStyleDeclaration(QLSParser.DefaultWithStyleDeclarationContext ctx) {
        Type questionType = (Type) ctx.type().accept(this);
        QLSWidget widget = (QLSWidget) ctx.widget().accept(this);
        List<StyleType> styleProperties = new ArrayList<>();

        for (QLSParser.StyleContext styleContext : ctx.style()) {
            StyleType style = (StyleType) styleContext.accept(this);
            styleProperties.add(style);
        }

        Style style = new Style(styleProperties, getLineNumber(ctx));
        return new DefaultStyle(questionType, style, widget, getLineNumber(ctx));
    }

    @Override
    public Node visitCheckbox(QLSParser.CheckboxContext ctx) {
        return new QLSCheckBox(ctx.getText(), getLineNumber(ctx));
    }

    @Override
    public Node visitRadio(QLSParser.RadioContext ctx) {
        String yesLabel = ctx.yes.getText();
        String noLabel = ctx.no.getText();
        return new QLSRadio(ctx.getText(), yesLabel, noLabel, getLineNumber(ctx));
    }

    @Override
    public Node visitDropdown(QLSParser.DropdownContext ctx) {
        String yesLabel = ctx.yes.getText();
        String noLabel = ctx.no.getText();
        return new QLSDropdown(ctx.getText(), yesLabel, noLabel, getLineNumber(ctx));
    }

    @Override
    public Node visitSpinbox(QLSParser.SpinboxContext ctx) {
        return new QLSSpinBox(ctx.getText(), getLineNumber(ctx));
    }

    @Override
    public Node visitSlider(QLSParser.SliderContext ctx) {
        return new QLSSlider(ctx.getText(), getLineNumber(ctx));
    }

    @Override
    public Node visitTextbox(QLSParser.TextboxContext ctx) {
        return new QLSTextBox(ctx.getText(),getLineNumber(ctx));
    }

    @Override
    public Node visitWidthStyle(QLSParser.WidthStyleContext ctx) {
        return new Width(Integer.parseInt(ctx.INTEGER().getText()), getLineNumber(ctx));
    }

    @Override
    public Node visitFontStyle(QLSParser.FontStyleContext ctx) {
        return new Font(ctx.STRING().getText(), getLineNumber(ctx));
    }

    @Override
    public Node visitFontsizeStyle(QLSParser.FontsizeStyleContext ctx) {
        return new FontSize(Integer.parseInt(ctx.INTEGER().getText()), getLineNumber(ctx));
    }

    @Override
    public Node visitColorStyle(QLSParser.ColorStyleContext ctx) {
        return new Color(Integer.decode(ctx.HEX().getText()), getLineNumber(ctx));
    }

    @Override
    public Node visitBooleanType(QLSParser.BooleanTypeContext ctx) {
        return new BooleanType(getLineNumber(ctx));
    }

    @Override
    public Node visitIntegerType(QLSParser.IntegerTypeContext ctx) {
        return new IntegerType(getLineNumber(ctx));
    }

    @Override
    public Node visitMoneyType(QLSParser.MoneyTypeContext ctx) {
        return new MoneyType(getLineNumber(ctx));
    }

    @Override
    public Node visitStringType(QLSParser.StringTypeContext ctx) {
        return new StringType(getLineNumber(ctx));
    }

    private LineNumber getLineNumber(ParserRuleContext ctx) {
        return new LineNumber(ctx.getStart().getLine());
    }
}
