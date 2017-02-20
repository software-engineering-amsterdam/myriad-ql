namespace OffByOne.Ql.Ast.Statements.Questions
{
    using OffByOne.Ql.Ast.Statements.Questions.Base;

    public class MoneyQuestionStatement : QuestionStatement
    {
        public MoneyQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
