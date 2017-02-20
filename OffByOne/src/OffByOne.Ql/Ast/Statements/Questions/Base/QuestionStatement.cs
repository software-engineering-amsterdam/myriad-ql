namespace OffByOne.Ql.Ast.Statements.Questions.Base
{
    using OffByOne.LanguageCore.Ast.Literals;

    public class QuestionStatement : Statement
    {
        // TODO: Debate whether we really need subclasses or if a Type field would be sufficient.
        protected QuestionStatement(string identifier, string question)
        {
            this.Identifier = identifier;
            this.Question = new StringLiteral(question);
        }

        public string Identifier { get; private set; }

        public StringLiteral Question { get; private set; }
    }
}
