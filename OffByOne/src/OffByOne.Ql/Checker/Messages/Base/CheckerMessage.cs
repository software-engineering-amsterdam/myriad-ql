namespace OffByOne.Ql.Checker.Messages.Base
{
    using System;

    using MoreDotNet.Wrappers;

    /// <summary>
    /// A message representing a problem found during a static code analysis.
    /// </summary>
    public abstract class CheckerMessage
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="CheckerMessage"/> class.
        /// </summary>
        /// <param name="message">The message.</param>
        /// <param name="description">The description.</param>
        /// <exception cref="ArgumentException">A valid, non-null, non-empty message must be given. - message</exception>
        protected CheckerMessage(string message, string description = "")
        {
            if (message.IsNullOrWhiteSpace())
            {
                throw new ArgumentException("A valid, non-null, non-empty message must be given.", nameof(message));
            }

            this.Message = message;
            this.Description = description;
        }

        /// <summary>
        /// Gets the message.
        /// </summary>
        /// <value>
        /// The message.
        /// </value>
        public string Message { get; }

        /// <summary>
        /// Gets the description.
        /// </summary>
        /// <value>
        /// The description.
        /// </value>
        public string Description { get; }

        /// <summary>
        /// Returns a <see cref="string" /> that represents this instance.
        /// </summary>
        /// <returns>
        /// A <see cref="string" /> that represents this instance.
        /// </returns>
        public override string ToString()
        {
            return $"{this.Message} - {this.Description}";
        }
    }
}
