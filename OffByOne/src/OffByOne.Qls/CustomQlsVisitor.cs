namespace OffByOne.Qls
{
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.Style;
    using OffByOne.LanguageCore.Ast.Style.Properties;
    using OffByOne.LanguageCore.Ast.Style.Properties.Base;
    using OffByOne.LanguageCore.Ast.Style.Rules;
    using OffByOne.LanguageCore.Ast.Style.Widgets;
    using OffByOne.LanguageCore.Ast.Style.Widgets.Base;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;

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

            var nodes = new List<AstNode>();
            nodes.AddRange(sections);
            nodes.AddRange(defaultBlocks);

            return new Page(id, nodes);
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

            var nodes = new List<AstNode>();
            nodes.AddRange(questions);
            nodes.AddRange(sections);
            nodes.AddRange(defaultBlocks);

            return new Section(name, nodes);
        }

        public override AstNode VisitQuestion(QlsGrammarParser.QuestionContext context)
        {
            var id = context.Identifier().GetText();
            var widget = (Widget)this.VisitWidget(context.widget());

            return new QuestionRule(id, widget, null);
        }

        public override AstNode VisitWidget(QlsGrammarParser.WidgetContext context)
        {
            // TODO: Fix for different types
            return new TextFieldWidget();
        }

        public override AstNode VisitDefaultBlock(QlsGrammarParser.DefaultBlockContext context)
        {
            var type = (ValueType)this.VisitType(context.type());
            var widget = (Widget)this.VisitWidget(context.widget());
            var styleRules = context
                .styleRule()
                .Select(x => (Property)this.Visit(x))
                .ToList();

            return new ValueTypeRule(type, widget, styleRules);
        }

        public override AstNode VisitStyleRule(QlsGrammarParser.StyleRuleContext context)
        {
            // TODO: Add a factory for properties
            return new ColorProperty("FAKE");
        }
    }
}
