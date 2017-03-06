namespace OffByOne.Ql.Checker
{
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Checker.Messages.Base;

    public class CheckerReport
    {
        private readonly IDictionary<string, IList<CheckerMessage>> messageCategories;

        public CheckerReport()
        {
            this.messageCategories = new Dictionary<string, IList<CheckerMessage>>();
        }

        public IEnumerable<CheckerMessage> InformationMessages => this.messageCategories[new InfoMessage(string.Empty).Level];

        public IEnumerable<CheckerMessage> Warnings => this.messageCategories[new WarningMessage(string.Empty).Level];

        public IEnumerable<CheckerMessage> Errors => this.messageCategories[new ErrorMessage(string.Empty).Level];

        public IEnumerable<CheckerMessage> AllMessages => this.messageCategories.SelectMany(x => x.Value);

        public void Add(CheckerMessage message)
        {
            message.AppendTo(this.messageCategories);
        }

        public void Add(IList<CheckerMessage> messages)
        {
            messages.ForEach(this.Add);
        }
    }
}
