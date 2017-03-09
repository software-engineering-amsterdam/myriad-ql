namespace OffByOne.Ql.Ast.Statements
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class IfStatement : Statement
    {
        public IfStatement(
            Expression condition,
            IEnumerable<Statement> statements,
            IEnumerable<Statement> elseStatements)
        {
            this.Condition = condition;
            this.Statements = statements;
            this.ElseStatements = elseStatements;
        }

        public Expression Condition { get; private set; }

        public IEnumerable<Statement> Statements { get; private set; }

        public IEnumerable<Statement> ElseStatements { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }

        public override ISet<string> GetDependencies()
        {
            return this.Condition.GetDependencies();
        }

        public bool IsComputable(string key)
        {
            return this.GetDependencies().Contains(key);
        }
    }
}
