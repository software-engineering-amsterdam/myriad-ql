namespace OffByOne.Ql.Ast.Statements.Branch
{
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
