namespace OffByOne.Qls.Checker
{
    using System;
    using System.Linq;

    using OffByOne.LanguageCore.Checker;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Visitors;

    public class StyleSheetAnalyzer
    {
        // TODO: Extract interfaces!
        private readonly FormCheckerVisitor formCheckerVisitor;

        public StyleSheetAnalyzer()
            : this(new FormCheckerVisitor())
        {
        }

        public StyleSheetAnalyzer(
            FormCheckerVisitor formCheckerVisitor)
        {
            if (formCheckerVisitor == null)
            {
                throw new ArgumentNullException(nameof(formCheckerVisitor), "A valid Type Visitor must provided.");
            }

            this.formCheckerVisitor = formCheckerVisitor;
        }

        public CheckerReport Check(StyleSheet node)
        {
            this.formCheckerVisitor.Visit(node, new FormCheckerContext());

            var finalReport = new CheckerReport();
            finalReport.Add(this.formCheckerVisitor.Report.AllMessages.ToList());

            return finalReport;
        }
    }
}
