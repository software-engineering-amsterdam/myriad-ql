namespace OffByOne.Ql.Checker
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Ql.Checker.Messages.Base;

    /// <summary>
    /// Holds all the information resulted from a QL program analysis.
    /// </summary>
    public class CheckerReport : ICheckerReport
    {
        private readonly ISet<CheckerMessage> checkerMessages;

        /// <summary>
        /// Initializes a new instance of the <see cref="CheckerReport"/> class with no starting <see cref="CheckerMessage"/>.
        /// </summary>
        public CheckerReport()
            : this(new List<CheckerMessage>())
        {
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="CheckerReport"/> class with
        /// a collection of starting <see cref="CheckerMessage"/>.
        /// </summary>
        /// <exception cref="ArgumentNullException">Thrown when a null collection of messages is given.</exception>
        /// <param name="messages">A starting set of <see cref="CheckerMessage"/>.</param>
        public CheckerReport(IEnumerable<CheckerMessage> messages)
        {
            if (messages == null)
            {
                throw new ArgumentNullException(nameof(messages));
            }

            this.checkerMessages = new HashSet<CheckerMessage>(messages);
        }

        /// <summary>
        /// Gets all the Information messages in the report.
        /// </summary>
        public IEnumerable<CheckerMessage> InformationMessages => this.checkerMessages
            .OfType<InfoMessage>();

        /// <summary>
        /// Gets all the warning messages in the report.
        /// </summary>
        public IEnumerable<CheckerMessage> Warnings => this.checkerMessages
            .OfType<WarningMessage>();

        /// <summary>
        /// Gets all the error messages in the report.
        /// </summary>
        public IEnumerable<CheckerMessage> Errors => this.checkerMessages
            .OfType<ErrorMessage>();

        /// <summary>
        /// Gets all the messages in the report.
        /// </summary>
        public IEnumerable<CheckerMessage> AllMessages => this.checkerMessages;

        /// <summary>
        /// Checks if any errors exist in the report.
        /// </summary>
        /// <returns>Return true if any errors exist in the report, false otherwise.</returns>
        public bool HasErrors() => this.Errors.Any();

        /// <summary>
        /// Adds a new <see cref="CheckerMessage"/> to the report.
        /// </summary>
        /// <exception cref="ArgumentNullException">Thrown when a null message is given.</exception>
        /// <param name="message">The new <see cref="CheckerMessage"/>.</param>
        public void Add(CheckerMessage message)
        {
            if (message == null)
            {
                throw new ArgumentNullException(nameof(message));
            }

            this.checkerMessages.Add(message);
        }

        /// <summary>
        /// Adds a new collection of <see cref="CheckerMessage"/> to the report.
        /// </summary>
        /// <exception cref="ArgumentNullException">Thrown when a null message collection is given.</exception>
        /// <param name="messages">A collection of <see cref="CheckerMessage"/> to be added to the report.</param>
        public void Add(IEnumerable<CheckerMessage> messages)
        {
            if (messages == null)
            {
                throw new ArgumentNullException(nameof(messages));
            }

            messages.ForEach(this.Add);
        }
    }
}
