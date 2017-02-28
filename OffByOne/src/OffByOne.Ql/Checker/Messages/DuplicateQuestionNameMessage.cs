namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Ql.Checker.Models;

    public class DuplicateQuestionNameMessage : CheckerMessage
    {
        public DuplicateQuestionNameMessage(QuestionStatement question, LogLevel level = LogLevel.Warning)
            : base($"Duplicate question name \"{question.Label}\" at: {question.SourceCode}", level)
        {
        }
    }
}
