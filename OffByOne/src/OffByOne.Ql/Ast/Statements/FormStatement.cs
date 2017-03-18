namespace OffByOne.Ql.Ast.Statements
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    public class FormStatement : Statement
    {
        public FormStatement(
            string identifier,
            IEnumerable<Statement> statements = null)
        {
            this.Identifier = identifier;
            this.Statements = statements;
        }

        public string Identifier { get; private set; }

        public IEnumerable<Statement> Statements { get; private set; }

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
