namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Messages.Base;

    /// <summary>
    /// A message representing a duplicate question identifier found during the static code analysis.
    /// </summary>
    /// <seealso cref="OffByOne.Ql.Checker.Messages.Base.ErrorMessage" />
    public class DuplicateQuestionIdentifierMessage : ErrorMessage
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="DuplicateQuestionIdentifierMessage"/> class.
        /// </summary>
        /// <param name="question">The question.</param>
        public DuplicateQuestionIdentifierMessage(QuestionStatement question)
            : base($"Duplicate question name \"{question.Identifier}\" at: {question.SourceCode}")
        {
        }
    }
}
