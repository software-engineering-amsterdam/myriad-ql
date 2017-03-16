namespace OffByOne.Ql.Checker.Messages.Base
{
    /// <summary>
    /// An message representing additional information found during the static code analysis.
    /// </summary>
    /// <seealso cref="CheckerMessage" />
    public abstract class InfoMessage : CheckerMessage
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="InfoMessage"/> class.
        /// </summary>
        /// <param name="message">The message.</param>
        /// <param name="description">The description.</param>
        protected InfoMessage(string message, string description = "")
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
            return "INFO: " + base.ToString();
        }
    }
}
