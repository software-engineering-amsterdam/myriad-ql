namespace OffByOne.Ql.Ast.Statements.Questions
{
    using OffByOne.Ql.Ast.Statements.Questions.Base;

    public class StringQuestionStatement : QuestionStatement
    {
        public StringQuestionStatement(
            string identifier,
            string question)
            : base(identifier, question)
        {
        }
    }
}
