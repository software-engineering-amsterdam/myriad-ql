namespace OffByOne.LanguageCore.Ast.Statements.Branch
{
    using OffByOne.LanguageCore.Ast.Expressions;
    public class IfStatement : Statement
    {
        protected IfStatement(Expression condition, Statement[] statements, ElseStatement elseStatement = null)
        {
            this.Condition = condition;
            this.Statements = statements;
            this.ElseStatement = elseStatement;
        }

        public Expression Condition { get; set; }

        public Statement[] Statements { get; private set; }

        public Statement ElseStatement { get; private set; }
    }
}
