namespace OffByOne.Ql.Ast.Statements
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    public class FormStatement : Statement
    {
        public FormStatement(
            string identifier,
            IEnumerable<Statement> statements = null)
        {
            if (identifier.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "A non-null, non-empty identifier must be given",
                    nameof(identifier));
            }

            this.Identifier = identifier;
            this.Statements = statements;
        }

        public string Identifier { get; }

        public IEnumerable<Statement> Statements { get; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }

        public override ISet<string> GetDependencies()
        {
            var identifiers = new SortedSet<string>();
            foreach (var statement in this.Statements)
            {
                identifiers.UnionWith(statement.GetDependencies());
            }

            return identifiers;
        }
    }
}
