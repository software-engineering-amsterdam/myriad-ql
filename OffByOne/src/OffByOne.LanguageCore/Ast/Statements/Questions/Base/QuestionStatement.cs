namespace OffByOne.LanguageCore.Ast.Statements.Questions.Base
{
    public class QuestionStatement : Statement
    {
        protected QuestionStatement(string identifier, string question)
        {
            this.Identifier = identifier;
            this.Question = question;
        }     

        public string Identifier { get; set; }
        public string Question { get; set; }

    }
}
