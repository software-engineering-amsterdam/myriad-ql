namespace OffByOne.LanguageCore.Ast.Statements
{
    using System.Collections;
    public class FormStatement : Statement
    {
        public FormStatement(string identifier, IList statements)
        {
            this.Identifier = identifier;
            this.Statements = statements;
        }

        public string Identifier { get; set; }

        public IList Statements { get; set; }
    }
}
