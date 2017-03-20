namespace OffByOne.Ql.Checker.Messages.Base
{
    /// <summary>
    /// An message representing a warning found during the static code analysis.
    /// </summary>
    /// <seealso cref="CheckerMessage" />
    public abstract class WarningMessage : CheckerMessage
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="WarningMessage"/> class.
        /// </summary>
        /// <param name="message">The message.</param>
        /// <param name="description">The description.</param>
        protected WarningMessage(string message, string description = "")
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
            return "WARNING: " + base.ToString();
        }
    }
}
