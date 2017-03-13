namespace OffByOne.Qls.Checker
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Analyzers;
    using OffByOne.Qls.Checker.Analyzers.Contracts;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class StyleSheetAnalyzer
    {
        private readonly IEnumerable<IAnalyzer> analyzers;

        public StyleSheetAnalyzer()
        {
            this.analyzers = new List<IAnalyzer>
            {
                new FormAnalyer(),
                new TypeAnalyzer()
            };
        }

        public StyleSheetAnalyzer(
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
            // TODO: Add mapings
            var questionMappings = new Dictionary<string, ValueType>();
            var finalReport = new CheckerReport();

            this.analyzers.ForEach(x => x.Analyze(styleNode, questionMappings));
            finalReport.Add(this.analyzers.SelectMany(x => x.Report.AllMessages));

            return finalReport;
        }
    }
}
