namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Messages.Base;

    /// <summary>
    /// A message representing a duplicate question label found during the static code analysis.
    /// </summary>
    /// <seealso cref="WarningMessage" />
    public class DuplicateQuestionLabelMessage : WarningMessage
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="DuplicateQuestionLabelMessage"/> class.
        /// </summary>
        /// <param name="question">The question.</param>
        public DuplicateQuestionLabelMessage(QuestionStatement question)
            : base($"Duplicate question label \"{question.Label}\" at: {question.SourceCode}")
        {
        }
    }
}
