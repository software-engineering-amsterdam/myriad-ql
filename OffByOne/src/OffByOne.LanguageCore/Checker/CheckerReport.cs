namespace OffByOne.LanguageCore.Checker
{
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.LanguageCore.Checker.Messages.Base;
    using OffByOne.LanguageCore.Checker.Messages.Models;

    public class CheckerReport
    {
        private readonly IDictionary<LogLevel, IList<CheckerMessage>> messageCategories;

        public CheckerReport()
        {
            this.messageCategories = new Dictionary<LogLevel, IList<CheckerMessage>>();
        }

        public IEnumerable<CheckerMessage> InformationMessages => this.messageCategories[LogLevel.Information];

        public IEnumerable<CheckerMessage> Warnings => this.messageCategories[LogLevel.Warning];

        public IEnumerable<CheckerMessage> Errors => this.messageCategories[LogLevel.Error];

        public IEnumerable<CheckerMessage> AllMessages => this.messageCategories.SelectMany(x => x.Value);

        public void Add(CheckerMessage message)
        {
            if (this.messageCategories.ContainsKey(message.Level))
            {
                this.messageCategories[message.Level].Add(message);
            }
            else
            {
                this.messageCategories[message.Level] = new List<CheckerMessage> { message };
            }
        }

        public void Add(IList<CheckerMessage> messages)
        {
            messages.ForEach(this.Add);
        }
    }
}
