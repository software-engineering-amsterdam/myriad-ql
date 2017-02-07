namespace OffByOne.LanguageCore.Ast.Statements.Questions
{
    using OffByOne.LanguageCore.Ast.Statements.Questions.Base;

    public class DecimalQuestionStatement : QuestionStatement
    {
        protected DecimalQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
