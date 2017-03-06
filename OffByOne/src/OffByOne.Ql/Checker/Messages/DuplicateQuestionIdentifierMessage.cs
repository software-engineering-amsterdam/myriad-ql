namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Messages.Base;

    public class DuplicateQuestionIdentifierMessage : ErrorMessage
    {
        public DuplicateQuestionIdentifierMessage(QuestionStatement question)
            : base($"Duplicate question name \"{question.Identifier}\" at: {question.SourceCode}")
        {
        }
    }
}
