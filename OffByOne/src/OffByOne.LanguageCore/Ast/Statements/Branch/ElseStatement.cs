namespace OffByOne.LanguageCore.Ast.Statements.Branch
{
    using System.Collections;
    using System.Collections.Generic;

    public class ElseStatement : Statement
    {
        public ElseStatement(IEnumerable<Statement> statements)
        {
            this.Statements = statements;
        }

        public IEnumerable<Statement> Statements { get; private set; }
    }
}
