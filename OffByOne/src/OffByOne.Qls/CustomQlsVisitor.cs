namespace OffByOne.Qls
{
    using System.Linq;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Style;
    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;

    public class CustomQlsVisitor : QlsGrammarBaseVisitor<AstNode>
    {
        public override AstNode VisitStylesheet(QlsGrammarParser.StylesheetContext context)
        {
            var id = context.Identifier().GetText();
            var nodes = context
                .styleSheetBody()
                .Select(this.VisitStyleSheetBody)
                .ToList();

            return new StyleSheet(id, nodes);
        }

        public override AstNode VisitPageStyleBody(QlsGrammarParser.PageStyleBodyContext context)
        {
            var id = context.Identifier().GetText();
            var nodes = context
                .pageBody()
                .Select(x => (Section)this.VisitPageBody(x))
                .ToList();

            return new Page(id, nodes);
        }

        public override AstNode VisitDefaultValueTypeRule(QlsGrammarParser.DefaultValueTypeRuleContext context)
        {
            var type = context.type().GetText();

            return base.VisitDefaultValueTypeRule(context);
        }

        ////// TODO: Move to a different class
        ////private ValueType GetValueType(string input)
        ////{
        ////    switch (input)
        ////    {
        ////        case "string":
        ////            return new StringValueType();
        ////    }
        ////}
    }
}
