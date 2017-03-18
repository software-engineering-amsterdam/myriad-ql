namespace OffByOne.Qls.Checker.Analyzers.Environment.Contracts
{
    using System.Collections.Generic;

    using OffByOne.Ql.Common.Visitors.Contracts;

    public interface IFormAnalyzerEnvironment : IEnvironment
    {
        ISet<string> PageLabels { get; }

        ISet<string> QuestionIdentifier { get; }

        ISet<string> SectionNames { get; }

        ISet<string> StyleSheetNames { get; set; }
    }
}