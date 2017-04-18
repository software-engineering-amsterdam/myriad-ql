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

    /// <summary>
    /// Checks a QL program for adherence to certain typing and structure rules.
    /// </summary>
    public class SyntaxChecker
    {
        private readonly IEnumerable<IAnalyzer> analyzers;

        /// <summary>
        /// Initializes a new instance of the <see cref="SyntaxChecker"/> class with the default set of <see cref="IAnalyzer"/>.
        /// </summary>
        public SyntaxChecker()
        {
            this.analyzers = new List<IAnalyzer>
            {
                new TypeAnalyzer(),
                new QuestionAnalyzer(),
                new CyclicDependencyAnalyzer(),
            };
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="SyntaxChecker"/> class with custom set of <see cref="IAnalyzer"/>,
        /// </summary>
        /// <param name="analyzers">List of <see cref="IAnalyzer"/> to be used for code analysis.</param>
        public SyntaxChecker(IEnumerable<IAnalyzer> analyzers)
        {
            this.analyzers = analyzers ?? throw new ArgumentNullException(nameof(analyzers), "A valid Collection of analyzers must provided.");
        }

        /// <summary>
        /// Checks a QL program and returns a <see cref="ICheckerReport"/> with all the errors, warnings and information messages.
        /// </summary>
        /// <param name="node">The starting node of the QL AST tree.</param>
        /// <returns>A <see cref="ICheckerReport"/> with all the errors, warnings and information messages</returns>
        public ICheckerReport Check(FormStatement node)
        {
            var finalReport = new CheckerReport();
            this.analyzers.ForEach(x => x.Analyze(node));
            finalReport.Add(this.analyzers.SelectMany(x => x.Report.AllMessages));

            return finalReport;
        }
    }
}
