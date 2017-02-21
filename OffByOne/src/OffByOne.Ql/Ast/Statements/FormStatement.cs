namespace OffByOne.Ql.Ast.Statements
{
    using System.Collections.Generic;

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

        public override TResult Accept<TResult>(IStatementVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
