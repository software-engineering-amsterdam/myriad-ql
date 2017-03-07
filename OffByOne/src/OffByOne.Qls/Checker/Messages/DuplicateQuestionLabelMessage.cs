namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.LanguageCore.Checker.Messages.Base;
    using OffByOne.LanguageCore.Checker.Models;
    using OffByOne.Qls.Ast.Style.Rules;

    public class DuplicateQuestionLabelMessage : CheckerMessage
    {
        public DuplicateQuestionLabelMessage(QuestionRule question, LogLevel level = LogLevel.Error)
            : base($"Duplicate questionn label {question.Name} at {question.SourceCode}", level)
        {
        }
    }
}
