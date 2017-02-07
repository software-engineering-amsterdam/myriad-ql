namespace OffByOne.LanguageCore.Ast.Statements.Questions
{
    using OffByOne.LanguageCore.Ast.Statements.Questions.Base;

    public class StringQuestionStatement : QuestionStatement
    {
        public StringQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
