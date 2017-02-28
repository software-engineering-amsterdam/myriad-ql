namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Ql.Checker.Models;

    public class DuplicateQuestionLabelMessage : CheckerMessage
    {
        public DuplicateQuestionLabelMessage(QuestionStatement question, LogLevel level = LogLevel.Error)
            : base($"Duplicate question label {question.Identifier} at: {question.SourceCode}", level)
        {
        }
    }
}
