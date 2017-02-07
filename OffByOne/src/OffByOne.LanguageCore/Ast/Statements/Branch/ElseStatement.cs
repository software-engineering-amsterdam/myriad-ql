namespace OffByOne.LanguageCore.Ast.Statements.Branch
{
    using System.Collections;

    public class ElseStatement : Statement
    {
        public ElseStatement(IList statements)
        {
            this.Statements = statements;
        }

        public IList Statements { get; private set; }
    }
}
