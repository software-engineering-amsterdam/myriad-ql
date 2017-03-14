namespace OffByOne.Ql.Checker.Contracts
{
    using System.Collections.Generic;

    using OffByOne.Ql.Checker.Messages.Base;

    public interface ICheckerReport
    {
        IEnumerable<CheckerMessage> AllMessages { get; }

        IEnumerable<CheckerMessage> Errors { get; }

        IEnumerable<CheckerMessage> InformationMessages { get; }

        IEnumerable<CheckerMessage> Warnings { get; }

        void Add(CheckerMessage message);

        void Add(IEnumerable<CheckerMessage> messages);

        bool ContainsErrors();
    }
}