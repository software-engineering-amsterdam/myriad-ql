namespace OffByOne.LanguageCore.Ast.Statements.Branch
{
    using System.Collections;
    using OffByOne.LanguageCore.Ast.Expressions;

    public class IfStatement : Statement
    {
        public IfStatement(Expression condition, IList statements, ElseStatement elseStatement = null)
        {
            this.Condition = condition;
            this.Statements = statements;
            this.ElseStatement = elseStatement;
        }

        public Expression Condition { get; set; }

        public IList Statements { get; private set; }

        public Statement ElseStatement { get; private set; }
    }
}
