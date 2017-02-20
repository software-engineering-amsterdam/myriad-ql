namespace OffByOne.Ql.Ast.Statements.Questions
{
    using OffByOne.Ql.Ast.Statements.Questions.Base;

    public class DecimalQuestionStatement : QuestionStatement
    {
        public DecimalQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
