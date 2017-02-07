namespace OffByOne.LanguageCore.Ast.Statements.Questions
{
    using OffByOne.LanguageCore.Ast.Statements.Questions.Base;

    public class BooleanQuestionStatement : QuestionStatement
    {
        protected BooleanQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
