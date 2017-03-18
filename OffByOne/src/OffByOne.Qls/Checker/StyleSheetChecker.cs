namespace OffByOne.Qls.Checker
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Collectors;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Analyzers;
    using OffByOne.Qls.Checker.Analyzers.Contracts;

    public class StyleSheetChecker
    {
        private readonly IEnumerable<IAnalyzer> analyzers;

        public StyleSheetChecker()
        {
            this.analyzers = new List<IAnalyzer>
            {
                new FormAnalyer(),
                new TypeAnalyzer(),
                new QuestionUsageAnalyzer()
            };
        }

        public StyleSheetChecker(
            IEnumerable<IAnalyzer> analyzers)
        {
            if (analyzers == null)
            {
                throw new ArgumentNullException(nameof(analyzers), "A valid collection of analyzers must provided.");
            }

            this.analyzers = analyzers;
        }

        public ICheckerReport Check(FormStatement structureNode, StyleSheet styleNode)
        {
            if (structureNode == null)
            {
                throw new ArgumentNullException(nameof(structureNode));
            }

            if (styleNode == null)
            {
                throw new ArgumentNullException(nameof(styleNode));
            }

            var collector = new QuestionCollector();
            collector.Collect(structureNode);
            var finalReport = new CheckerReport();

            this.analyzers.ForEach(x => x.Analyze(styleNode, collector.Mappings));
            finalReport.Add(this.analyzers.SelectMany(x => x.Report.AllMessages));

            return finalReport;
        }
    }
}
