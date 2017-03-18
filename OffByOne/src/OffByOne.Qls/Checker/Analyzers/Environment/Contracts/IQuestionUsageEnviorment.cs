namespace OffByOne.Qls.Checker.Analyzers.Environment.Contracts
{
    using System.Collections.Generic;

    using OffByOne.Ql.Common.Visitors.Contracts;

    public interface IQuestionUsageEnviorment : IEnvironment
    {
        bool HasUnusedQuestions { get; }

        IEnumerable<string> UnusedQuestions { get; }

        void AddQuestion(string question);

        bool HasQuestion(string question);

        void MarkQuestionAsUsed(string question);
    }
}