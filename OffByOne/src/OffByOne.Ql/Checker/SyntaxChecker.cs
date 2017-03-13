namespace OffByOne.Ql.Checker
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Analyzers;
    using OffByOne.Ql.Checker.Analyzers.Contracts;
    using OffByOne.Ql.Checker.Contracts;

    public class SyntaxChecker
    {
        private readonly IEnumerable<IAnalyzer> analyzers;

        public SyntaxChecker()
        {
            this.analyzers = new List<IAnalyzer>
            {
                new TypeAnalyzer(),
                new QuestionAnalyzer(),
                new CyclicDependencyAnalyzer()
            };
        }

        public SyntaxChecker(IEnumerable<IAnalyzer> analyzers)
        {
            if (analyzers == null)
            {
                throw new ArgumentNullException(nameof(analyzers), "A valid Collection of analyzers must provided.");
            }

            this.analyzers = analyzers;
        }

        public ICheckerReport Check(FormStatement node)
        {
            var finalReport = new CheckerReport();
            this.analyzers.ForEach(x => x.Analyze(node));
            finalReport.Add(this.analyzers.SelectMany(x => x.Report.AllMessages));

            return finalReport;
        }
    }
}
