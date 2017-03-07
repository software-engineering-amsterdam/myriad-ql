namespace OffByOne.Ql.Checker.Messages.Base
{
    using System;
    using System.Collections.Generic;

    public abstract class CheckerMessage
    {
        protected CheckerMessage(string message, string description = "")
        {
            this.Message = message;
            this.Description = description;
        }

        public string Message { get; }

        public string Description { get; }

        public override string ToString()
        {
            return $"{this.Message} - {this.Description}";
        }

        public void AppendTo(ISet<CheckerMessage> checerMessages)
        {
            if (checerMessages == null)
            {
                throw new ArgumentNullException(nameof(checerMessages));
            }

            checerMessages.Add(this);
        }
    }
}
