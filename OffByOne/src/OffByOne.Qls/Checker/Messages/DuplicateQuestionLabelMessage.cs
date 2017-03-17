namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Qls.Ast.Style.Rules;

    public class DuplicateQuestionLabelMessage : ErrorMessage
    {
        public DuplicateQuestionLabelMessage(QuestionRule question)
            : base($"Duplicate question label \"{question.Identifier}\" at: {question.SourceCode}")
        {
        }
    }
}
