namespace OffByOne.LanguageCore.Ast.Statements.Questions
{
    using OffByOne.LanguageCore.Ast.Statements.Questions.Base;

    public class IntegerQuestionStatement : QuestionStatement
    {
        protected IntegerQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
