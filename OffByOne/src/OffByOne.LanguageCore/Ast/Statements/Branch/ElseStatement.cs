namespace OffByOne.LanguageCore.Ast.Statements.Branch
{
    using OffByOne.LanguageCore.Ast.Expressions;
    public class ElseStatement : Statement
    {
        protected ElseStatement(Statement[] statements)
        {
            this.Statements = statements;
        }

        public Statement[] Statements { get; private set; }
    }
}
