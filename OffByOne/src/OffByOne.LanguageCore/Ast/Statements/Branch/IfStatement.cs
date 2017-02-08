namespace OffByOne.LanguageCore.Ast.Statements.Branch
{
    using System.Collections;
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Expressions;

    public class IfStatement : Statement
    {
        public IfStatement(
            Expression condition,
            IReadOnlyList<Statement> statements,
            ElseStatement elseStatement = null)
        {
            this.Condition = condition;
            this.Statements = statements;
            this.ElseStatement = elseStatement;
        }

        public Expression Condition { get; private set; }

        public IReadOnlyList<Statement> Statements { get; private set; }

        public Statement ElseStatement { get; private set; }
    }
}
