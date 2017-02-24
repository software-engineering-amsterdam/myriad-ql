namespace OffByOne.Ql.Visitors
{
    using OffByOne.LanguageCore.Visitors;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Visitors.Base;

    public class QuestionVisitor : BaseQlVisitor<object, VisitorContext>
    {
        public override object Visit(QuestionStatement expression, VisitorContext context)
        {
            return base.Visit(expression, context);
        }
    }
}
