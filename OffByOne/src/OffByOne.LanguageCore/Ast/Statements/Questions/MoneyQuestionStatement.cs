namespace OffByOne.LanguageCore.Ast.Statements.Questions
{
    using OffByOne.LanguageCore.Ast.Statements.Questions.Base;

    public class MoneyQuestionStatement : QuestionStatement
    {
        protected MoneyQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
