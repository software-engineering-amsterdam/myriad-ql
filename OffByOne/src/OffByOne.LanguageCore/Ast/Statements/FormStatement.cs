namespace OffByOne.LanguageCore.Ast.Statements
{
    using System.Collections.Generic;

    public class FormStatement : Statement
    {
        public FormStatement(string identifier, IList<Statement> statements)
        {
            this.Identifier = identifier;
            this.Statements = statements;
        }

        public string Identifier { get; set; }

        public IList<Statement> Statements { get; set; }
    }
}
