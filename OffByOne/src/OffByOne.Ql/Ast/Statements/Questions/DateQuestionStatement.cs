namespace OffByOne.Ql.Ast.Statements.Questions
{
    using OffByOne.Ql.Ast.Statements.Questions.Base;

    public class DateQuestionStatement : QuestionStatement
    {
        public DateQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
