namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;

    /// <summary>
    /// A message representing a circular dependency found during the static code analysis.
    /// </summary>
    /// <seealso cref="ErrorMessage" />
    public class CircularDependencyMessage : ErrorMessage
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="CircularDependencyMessage"/> class.
        /// </summary>
        /// <param name="questionId">The question identifier.</param>
        /// <param name="description">The description.</param>
        public CircularDependencyMessage(string questionId, string description = "")
            : base($"Circular dependency for question {questionId}!", description)
        {
        }
    }
}
