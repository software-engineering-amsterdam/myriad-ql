namespace OffByOne.Ql.Ast.Statements.Questions
{
    using OffByOne.Ql.Ast.Statements.Questions.Base;

    public class BooleanQuestionStatement : QuestionStatement
    {
        public BooleanQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
