namespace OffByOne.Ql.Ast.Statements.Questions
{
    using OffByOne.Ql.Ast.Statements.Questions.Base;

    public class IntegerQuestionStatement : QuestionStatement
    {
        public IntegerQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
