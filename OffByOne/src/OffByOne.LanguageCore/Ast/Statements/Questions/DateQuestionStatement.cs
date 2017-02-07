namespace OffByOne.LanguageCore.Ast.Statements.Questions
{
    using OffByOne.LanguageCore.Ast.Statements.Questions.Base;

    public class DateQuestionStatement : QuestionStatement
    {
        protected DateQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
