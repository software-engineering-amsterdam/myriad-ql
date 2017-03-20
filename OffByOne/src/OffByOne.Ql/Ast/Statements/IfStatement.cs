namespace OffByOne.Ql.Ast.Statements
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    public class IfStatement : Statement
    {
        public IfStatement(
            Expression condition,
            IEnumerable<Statement> statements,
            IEnumerable<Statement> elseStatements)
        {
            if (condition == null)
            {
                throw new ArgumentNullException(nameof(condition));
            }

            if (statements == null)
            {
                throw new ArgumentNullException(nameof(condition));
            }

            this.Condition = condition;
            this.Statements = statements;
            this.ElseStatements = elseStatements;
        }

        public Expression Condition { get; }

        public IEnumerable<Statement> Statements { get; }

        public IEnumerable<Statement> ElseStatements { get; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
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
