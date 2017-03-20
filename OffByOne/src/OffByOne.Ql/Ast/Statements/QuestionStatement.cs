namespace OffByOne.Ql.Ast.Statements
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class QuestionStatement : Statement
    {
        public QuestionStatement(
            string identifier,
            ValueType type,
            StringLiteral label,
            Expression computationExpression = null)
        {
            if (identifier.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "A non-null, non-empty identifier must be given",
                    nameof(identifier));
            }

            if (type == null)
            {
                throw new ArgumentNullException(nameof(type));
            }

            if (label == null)
            {
                throw new ArgumentNullException(nameof(label));
            }

            this.Identifier = identifier;
            this.Type = type;
            this.Label = label;
            this.ComputationExpression = computationExpression;
        }

        public string Identifier { get; }

        public ValueType Type { get; }

        public StringLiteral Label { get; }

        public Expression ComputationExpression { get; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }

        public override ISet<string> GetDependencies()
        {
            var identifiers = new SortedSet<string>();
            if (this.ComputationExpression != null)
            {
                identifiers.UnionWith(this.ComputationExpression.GetDependencies());
            }

            return identifiers;
        }

        public bool IsComputable(string key)
        {
            var hasExpression = this.ComputationExpression != null;
            var hasDependency = this.GetDependencies().Contains(key);
            return hasExpression && hasDependency;
        }
    }
}
