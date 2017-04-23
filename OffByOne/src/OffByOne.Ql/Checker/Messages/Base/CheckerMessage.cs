namespace OffByOne.Ql.Checker.Messages.Base
{
    using System;

    using MoreDotNet.Wrappers;

    public abstract class CheckerMessage
    {
        protected CheckerMessage(string message, string description = "")
        {
            if (message.IsNullOrWhiteSpace())
            {
                throw new ArgumentException("A valid, non-null, non-empty message must be given.", nameof(message));
            }

            this.Message = message;
            this.Description = description;
        }

        public string Message { get; }

        public string Description { get; }

        public override string ToString()
        {
            return $"{this.Message} - {this.Description}";
        }
    }
}
