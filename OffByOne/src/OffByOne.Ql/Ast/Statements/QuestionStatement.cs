namespace OffByOne.Ql.Ast.Statements
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Visitors.Contracts;

    public class QuestionStatement : Statement
    {
        public QuestionStatement(
            string identifier,
            ValueType type,
            LiteralExpression question,
            Expression value = null)
        {
            this.Identifier = identifier;
            this.Type = type;
            this.Question = question;
            this.ComputedValue = value;
        }

        public string Identifier { get; private set; }

        public ValueType Type { get; private set; }

        public LiteralExpression Question { get; private set; }

        public Expression ComputedValue { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
