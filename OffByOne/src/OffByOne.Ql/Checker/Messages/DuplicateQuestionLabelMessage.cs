namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Messages.Base;

    public class DuplicateQuestionLabelMessage : WarningMessage
    {
        public DuplicateQuestionLabelMessage(QuestionStatement question)
            : base($"Duplicate question label \"{question.Label}\" at: {question.SourceCode}")
        {
        }
    }
}
