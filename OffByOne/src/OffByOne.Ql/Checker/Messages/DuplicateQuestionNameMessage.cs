namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.LanguageCore.Checker.Messages.Base;
    using OffByOne.LanguageCore.Checker.Models;
    using OffByOne.Ql.Ast.Statements;

    public class DuplicateQuestionNameMessage : CheckerMessage
    {
        public DuplicateQuestionNameMessage(QuestionStatement question, LogLevel level = LogLevel.Warning)
            : base($"Duplicate question name \"{question.Label}\" at: {question.SourceCode}", level)
        {
        }
    }
}
