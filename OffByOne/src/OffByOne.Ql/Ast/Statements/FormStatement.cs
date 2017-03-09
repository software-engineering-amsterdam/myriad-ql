namespace OffByOne.Ql.Ast.Statements
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class FormStatement : Statement
    {
        public FormStatement(string identifier, IEnumerable<Statement> statements)
        {
            this.Identifier = identifier;
            this.Statements = statements;
        }

        public string Identifier { get; private set; }

        public IEnumerable<Statement> Statements { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
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
