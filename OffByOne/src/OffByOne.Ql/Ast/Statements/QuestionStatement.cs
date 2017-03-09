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
            Expression computationExpression = null)
        {
            this.Identifier = identifier;
            this.Type = type;
            this.Label = label;
            this.ComputationExpression = computationExpression;
        }

        public string Identifier { get; private set; }

        public ValueType Type { get; private set; }

        public StringLiteral Label { get; private set; }

        public Expression ComputationExpression { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
