namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.LanguageCore.Checker.Messages.Base;
    using OffByOne.LanguageCore.Checker.Models;
    using OffByOne.Ql.Ast.Statements;

    public class DuplicateQuestionLabelMessage : CheckerMessage
    {
        public DuplicateQuestionLabelMessage(QuestionStatement question, LogLevel level = LogLevel.Error)
            : base($"Duplicate question label {question.Identifier} at: {question.SourceCode}", level)
        {
        }
    }
}
