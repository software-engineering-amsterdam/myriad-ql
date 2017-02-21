namespace OffByOne.Ql.Ast.Statements.Branch
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Expressions.Base;

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
    }
}
