namespace OffByOne.Ql.Ast.Statements
{
    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class QuestionStatement : Statement
    {
        public QuestionStatement(
            string identifier,
            ValueType type,
            StringLiteral label,
            Expression value = null)
        {
            this.Identifier = identifier;
            this.Type = type;
            this.Label = label;
            this.ComputedValue = value;
        }

        public string Identifier { get; private set; }

        public ValueType Type { get; private set; }

        public StringLiteral Label { get; private set; }

        public Expression ComputedValue { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
