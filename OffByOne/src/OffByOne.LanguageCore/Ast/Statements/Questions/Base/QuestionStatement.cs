namespace OffByOne.LanguageCore.Ast.Statements.Questions.Base
{
    public class QuestionStatement : Statement
    {
        // TODO: Debate whether we really need subclasses or if a Type field would be sufficient.
        protected QuestionStatement(string identifier, string question)
        {
            this.Identifier = identifier;
            this.Question = question;
        }

        public string Identifier { get; private set; }

        public string Question { get; private set; }
    }
}
