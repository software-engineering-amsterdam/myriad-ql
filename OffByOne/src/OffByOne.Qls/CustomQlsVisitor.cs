namespace OffByOne.Qls
{
    using System.Linq;

    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Qls.Ast.Style.Properties;
    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Ast.Style.Widgets;
    using OffByOne.Qls.Ast.Style.Widgets.Base;

    public class CustomQlsVisitor : QlsGrammarBaseVisitor<AstNode>
    {
        public override AstNode VisitStylesheet(QlsGrammarParser.StylesheetContext context)
        {
            var id = context.Identifier().GetText();
            var pages = context
                .page()
                .Select(x => (Page)this.VisitPage(x))
                .ToList();

            return new StyleSheet(id, pages);
        }

        public override AstNode VisitPage(QlsGrammarParser.PageContext context)
        {
            var id = context
                .Identifier()
                .GetText();

            var test = context.section().ToList();

            // TODO: Do I need cast?
            var sections = context
                .section()
                .Select(x => (Section)this.VisitSection(x))
                .ToList();

            var defaultBlocks = context
                .defaultBlock()
                .Select(x => (ValueTypeRule)this.VisitDefaultBlock(x))
                .ToList();

            return new Page(id, sections, defaultBlocks);
        }

        public override AstNode VisitSection(QlsGrammarParser.SectionContext context)
        {
            var name = new StringLiteral(context.StringLiteral().GetText());

            var questions = context
                .question()
                .Select(x => (QuestionRule)this.VisitQuestion(x));

            var sections = context
                .section()
                .Select(x => (Section)this.VisitSection(x));

            var defaultBlocks = context
                .defaultBlock()
                .Select(x => (ValueTypeRule)this.VisitDefaultBlock(x))
                .ToList();

            return new Section(name, sections, questions, defaultBlocks);
        }

        public override AstNode VisitQuestion(QlsGrammarParser.QuestionContext context)
        {
            var id = context.Identifier().GetText();
            if (context.widget() == null)
            {
                return new QuestionRule(id, null, null);
            }

            var widget = (Widget)this.Visit(context.widget());

            return new QuestionRule(id, widget, null);
        }

        public override AstNode VisitDefaultBlock(QlsGrammarParser.DefaultBlockContext context)
        {
            var type = (ValueType)this.Visit(context.type());
            var widget = (Widget)this.VisitWidget(context.widget());
            var styleRules = context
                .styleRule()
                .Select(x => (Property)this.Visit(x))
                .ToList();

            return new ValueTypeRule(type, widget, styleRules);
        }

        public override AstNode VisitHeightStyleRule(QlsGrammarParser.HeightStyleRuleContext context)
        {
            return new HeightProperty(new IntegerLiteral(context.IntegerLiteral().GetText()));
        }

        public override AstNode VisitWidthStyleRule(QlsGrammarParser.WidthStyleRuleContext context)
        {
            return new WidthProperty(new IntegerLiteral(context.IntegerLiteral().GetText()));
        }

        public override AstNode VisitColorStyleRule(QlsGrammarParser.ColorStyleRuleContext context)
        {
            return new ColorProperty(new HexLiteral(context.HexColorLiteral().GetText()));
        }

        public override AstNode VisitFontNameStyleRule(QlsGrammarParser.FontNameStyleRuleContext context)
        {
            return new FontNameProperty(new StringLiteral(context.StringLiteral().GetText()));
        }

        public override AstNode VisitFontSizeStyleRule(QlsGrammarParser.FontSizeStyleRuleContext context)
        {
            return new FontSizeProperty(new IntegerLiteral(context.IntegerLiteral().GetText()));
        }

        public override AstNode VisitFontStyleStyleRule(QlsGrammarParser.FontStyleStyleRuleContext context)
        {
            return new FontStyleProperty(new StringLiteral(context.StringLiteral().GetText()));
        }

        public override AstNode VisitCheckboxWidgetType(QlsGrammarParser.CheckboxWidgetTypeContext context)
        {
            return new CheckBoxWidget();
        }

        public override AstNode VisitSpinboxWidgetType(QlsGrammarParser.SpinboxWidgetTypeContext context)
        {
            return new SpinboxWidget();
        }

        public override AstNode VisitRadioWidgetType(QlsGrammarParser.RadioWidgetTypeContext context)
        {
            var options = (OptionsList<StringLiteral>)this.Visit(context.optionsList());
            return new RadioButtonWidget(options);
        }

        public override AstNode VisitDropdownWidgetType(QlsGrammarParser.DropdownWidgetTypeContext context)
        {
            var options = (OptionsList<StringLiteral>)this.Visit(context.optionsList());
            return new DropDownWidget(options);
        }

        public override AstNode VisitBooleanLiteralType(QlsGrammarParser.BooleanLiteralTypeContext context)
        {
            return new BooleanLiteral(context.GetText());
        }

        public override AstNode VisitIntegerLiteralType(QlsGrammarParser.IntegerLiteralTypeContext context)
        {
            return new IntegerLiteral(context.GetText());
        }

        public override AstNode VisitStringLiteralType(QlsGrammarParser.StringLiteralTypeContext context)
        {
            return new StringLiteral(context.GetText());
        }

        public override AstNode VisitHexLiteralType(QlsGrammarParser.HexLiteralTypeContext context)
        {
            return new HexLiteral(context.GetText());
        }

        public override AstNode VisitDateLiteralType(QlsGrammarParser.DateLiteralTypeContext context)
        {
            return new DateLiteral(context.GetText());
        }

        public override AstNode VisitDecimalLiteralType(QlsGrammarParser.DecimalLiteralTypeContext context)
        {
            return new DecimalLiteral(context.GetText());
        }

        public override AstNode VisitMoneyLiteralType(QlsGrammarParser.MoneyLiteralTypeContext context)
        {
            return new MoneyLiteral(context.GetText());
        }

        public override AstNode VisitOptionsList(QlsGrammarParser.OptionsListContext context)
        {
            return (OptionsList<StringLiteral>)this.VisitOption(context.option());
        }

        public override AstNode VisitOption(QlsGrammarParser.OptionContext context)
        {
            var outputList = new OptionsList<StringLiteral>
            {
                new StringLiteral(context.StringLiteral().GetText())
            };

            if (context.option() == null)
            {
                return outputList;
            }

            outputList.AddRange((OptionsList<StringLiteral>)this.VisitOption(context.option()));

            return outputList;
        }

        public override AstNode VisitBooleanType(QlsGrammarParser.BooleanTypeContext context)
        {
            return TypeConstants.BooleanType;
        }

        public override AstNode VisitIntegerType(QlsGrammarParser.IntegerTypeContext context)
        {
            return TypeConstants.IntegerType;
        }

        public override AstNode VisitFloatType(QlsGrammarParser.FloatTypeContext context)
        {
            return TypeConstants.DecimalType;
        }

        public override AstNode VisitMoneyType(QlsGrammarParser.MoneyTypeContext context)
        {
            return TypeConstants.MoneyType;
        }

        public override AstNode VisitStringType(QlsGrammarParser.StringTypeContext context)
        {
            return TypeConstants.StringType;
        }

        public override AstNode VisitDateType(QlsGrammarParser.DateTypeContext context)
        {
            return TypeConstants.DateType;
        }
    }
}
