namespace OffByOne.Ql.Checker.Messages.Base
{
    /// <summary>
    /// An message representing an error found during the static code analysis.
    /// </summary>
    /// <seealso cref="CheckerMessage" />
    public abstract class ErrorMessage : CheckerMessage
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="ErrorMessage"/> class.
        /// </summary>
        /// <param name="message">The message.</param>
        /// <param name="description">The description.</param>
        protected ErrorMessage(string message, string description = "")
            : base(message, description)
        {
        }

        /// <summary>
        /// Returns a <see cref="string" /> that represents this instance.
        /// </summary>
        /// <returns>
        /// A <see cref="string" /> that represents this instance.
        /// </returns>
        public override string ToString()
        {
            return $"ERROR: {this.Message} - {this.Description}";
        }
    }
}
