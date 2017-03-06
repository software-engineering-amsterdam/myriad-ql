namespace OffByOne.Ql.Checker.Messages.Base
{
    using System;
    using System.Collections.Generic;

    public abstract class CheckerMessage
    {
        protected CheckerMessage(string level, string message, string description = "")
        {
            this.Level = level;
            this.Message = message;
            this.Description = description;
        }

        public string Level { get; }

        public string Message { get; }

        public string Description { get; }

        public override string ToString()
        {
            return $"{this.Level.ToUpper()}: {this.Message} - {this.Description}";
        }

        public void AppendTo(IDictionary<string, IList<CheckerMessage>> messageCategories)
        {
            if (messageCategories.ContainsKey(this.Level))
            {
                messageCategories[this.Level].Add(this);
            }
            else
            {
                messageCategories[this.Level] = new List<CheckerMessage> { this };
            }
        }
    }
}
