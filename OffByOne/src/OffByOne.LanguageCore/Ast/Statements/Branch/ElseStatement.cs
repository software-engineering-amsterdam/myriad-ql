namespace OffByOne.LanguageCore.Ast.Statements.Branch
{
    using System.Collections;
    using System.Collections.Generic;

    public class ElseStatement : Statement
    {
        public ElseStatement(IList<Statement> statements)
        {
            this.Statements = statements;
        }

        public IList<Statement> Statements { get; private set; }
    }
}
