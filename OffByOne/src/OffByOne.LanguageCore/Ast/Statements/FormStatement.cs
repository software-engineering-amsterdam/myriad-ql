namespace OffByOne.LanguageCore.Ast.Statements
{
    public class FormStatement : Statement
    {
        protected FormStatement(string identifier, Statement[] statements)
        {
            this.Identifier = identifier;
            this.Statements = statements;
        }

        public string Identifier { get; set; }

        public Statement[] Statements { get; set; }
    }
}
