namespace OffByOne.Ql.Ast.Statements.Branch
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Ast.Expressions;

    public class IfStatement : Statement
    {
        public IfStatement(
            Expression condition,
            IEnumerable<Statement> statements,
            ElseStatement elseStatement = null)
        {
            this.Condition = condition;
            this.Statements = statements;
            this.ElseStatement = elseStatement;
        }

        public Expression Condition { get; private set; }

        public IEnumerable<Statement> Statements { get; private set; }

        public ElseStatement ElseStatement { get; private set; }
    }
}
