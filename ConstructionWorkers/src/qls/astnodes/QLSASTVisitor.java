package qls.astnodes;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import ql.astnodes.types.*;
import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.astnodes.sections.DefaultStyle;
import qls.astnodes.sections.Section;
import qls.astnodes.styles.*;
import qls.astnodes.widgets.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGGX on 04-Mar-17.
 */

public class QLSASTVisitor extends QLSBaseVisitor<Node> {

    private final StyleSheet abstractSyntaxTree;

    public QLSASTVisitor(ParseTree parseTree) {
        abstractSyntaxTree = (StyleSheet) parseTree.accept(this);
    }

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

        StyleSheet styleSheet = new StyleSheet(sections, defaultStyles, name, getLineNumber(ctx));
        return styleSheet;
    }
    

    @Override
    public Node visitSection(QLSParser.SectionContext ctx) {
        String name = ctx.STRING().getText();

        List<Section> sections = new ArrayList<>();
        for (QLSParser.SectionContext sectionContext : ctx.section()) {
            Section section = (Section) sectionContext.accept(this);
            sections.add(section);
        }

        List<StyleQuestion> questions = new ArrayList<>();
        for (QLSParser.QuestionContext questionContext : ctx.question()) {
            StyleQuestion question = (StyleQuestion) questionContext.accept(this);
            questions.add(question);
        }

        List<DefaultStyle> defaultStyles = new ArrayList<>();
        for (QLSParser.DefaultStyleContext defaultStyleContext : ctx.defaultStyle()) {
            DefaultStyle defaultStyle = (DefaultStyle) defaultStyleContext.accept(this);
            defaultStyles.add(defaultStyle);
        }

        Section section = new Section(name, sections, defaultStyles, questions, getLineNumber(ctx));
        return section;
    }

    @Override
    public Node visitWidgetQuestion(QLSParser.WidgetQuestionContext ctx) {
        String identifier = ctx.Identifier().getText();

        QLSWidget widget = (QLSWidget) ctx.widget().accept(this);
        widget.setLabel(identifier);

        StyleQuestion qlsQuestion = new StyleQuestion(identifier, widget, getLineNumber(ctx));
        return qlsQuestion;
    }

    @Override
    public Node visitNormalQuestion(QLSParser.NormalQuestionContext ctx) {
        String identifier = ctx.Identifier().getText();

        StyleQuestion qlsQuestion = new StyleQuestion(identifier, new QLSUndefinedWidget(getLineNumber(ctx)), getLineNumber(ctx));
        return qlsQuestion;
    }

    @Override
    public Node visitDefaultWithoutStyleDeclaration(QLSParser.DefaultWithoutStyleDeclarationContext ctx) {
        Type questionType = (Type) ctx.type().accept(this);
        QLSWidget widget = (QLSWidget) ctx.widget().accept(this);

        DefaultStyle defaultStyleDeclaration =
                new DefaultStyle(new UndefinedStyle(getLineNumber(ctx)), widget.getType(), questionType, getLineNumber(ctx));

        return defaultStyleDeclaration;
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

        DefaultStyle defaultStyleDeclaration =
                new DefaultStyle(style, widget.getType(), questionType, getLineNumber(ctx));

        return defaultStyleDeclaration;
    }

    @Override
    public Node visitCheckbox(QLSParser.CheckboxContext ctx) {
        QLSCheckBox widget = new QLSCheckBox(ctx.getText(), getLineNumber(ctx));
        return widget;
    }

    @Override
    public Node visitRadio(QLSParser.RadioContext ctx) {
        String yesLabel = ctx.yes.getText();
        String noLabel = ctx.no.getText();

        QLSRadio widget = new QLSRadio(ctx.getText(), yesLabel, noLabel, getLineNumber(ctx));
        return widget;
    }

    @Override
    public Node visitDropdown(QLSParser.DropdownContext ctx) {
        String yesLabel = ctx.yes.getText();
        String noLabel = ctx.no.getText();
        QLSDropdown widget = new QLSDropdown(ctx.getText(), yesLabel, noLabel, getLineNumber(ctx));
        return widget;
    }

    @Override
    public Node visitSpinbox(QLSParser.SpinboxContext ctx) {
        QLSSpinBox widget = new QLSSpinBox(ctx.getText(), getLineNumber(ctx));
        return widget;
    }

    @Override
    public Node visitSlider(QLSParser.SliderContext ctx) {
        QLSSlider widget = new QLSSlider(ctx.getText(), getLineNumber(ctx));
        return widget;
    }

    @Override
    public Node visitTextbox(QLSParser.TextboxContext ctx) {
        QLSTextBox widget = new QLSTextBox(ctx.getText(),getLineNumber(ctx));
        return widget;
    }

    @Override
    public Node visitWidthStyle(QLSParser.WidthStyleContext ctx) {
        Width style = new Width(Integer.parseInt(ctx.NUMBER().getText()), getLineNumber(ctx));
        return style;
    }

    @Override
    public Node visitFontStyle(QLSParser.FontStyleContext ctx) {
        Font style = new Font(ctx.STRING().getText(), getLineNumber(ctx));
        return style;
    }

    @Override
    public Node visitFontsizeStyle(QLSParser.FontsizeStyleContext ctx) {
        FontSize style = new FontSize(Integer.parseInt(ctx.NUMBER().getText()), getLineNumber(ctx));
        return style;
    }

    @Override
    public Node visitColorStyle(QLSParser.ColorStyleContext ctx) {
        Color style = new Color(
                Integer.decode(ctx.HEX().getText()), getLineNumber(ctx)
        );
        return style;
    }

    @Override
    public Node visitBoolType(QLSParser.BoolTypeContext ctx) {
        BooleanType type = new BooleanType(getLineNumber(ctx));
        return type;
    }

    @Override
    public Node visitIntType(QLSParser.IntTypeContext ctx) {
        IntegerType type = new IntegerType(getLineNumber(ctx));
        return type;
    }

    @Override
    public Node visitMoneyType(QLSParser.MoneyTypeContext ctx) {
        MoneyType type = new MoneyType(getLineNumber(ctx));
        return type;
    }

    @Override
    public Node visitStringType(QLSParser.StringTypeContext ctx) {
        StringType type = new StringType(getLineNumber(ctx));
        return type;
    }

    private LineNumber getLineNumber(ParserRuleContext ctx) {
        return new LineNumber(ctx.getStart().getLine());
    }
}
