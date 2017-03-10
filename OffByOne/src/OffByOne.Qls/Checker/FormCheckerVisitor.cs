namespace OffByOne.Qls.Checker
{
    using OffByOne.Ql.Checker;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Messages;
    using OffByOne.Qls.Visitors;
    using OffByOne.Qls.Visitors.Base;

    public class FormCheckerVisitor : BaseQlsVisitor<object, FormCheckerEnvironment>
    {
        public FormCheckerVisitor()
            : this(new CheckerReport())
        {
        }

        public FormCheckerVisitor(CheckerReport report)
        {
            this.Report = report;
        }

        public CheckerReport Report { get; }

        public override object Visit(StyleSheet expression, FormCheckerEnvironment environment)
        {
            if (environment.StyleSheetNames.Contains(expression.Id))
            {
                this.Report.Add(new DuplicateStyleSheetMesssage(expression));
            }
            else
            {
                environment.StyleSheetNames.Add(expression.Id);
            }

            return base.Visit(expression, environment);
        }

        public override object Visit(Page expression, FormCheckerEnvironment environment)
        {
            if (environment.PageLabels.Contains(expression.Id))
            {
                this.Report.Add(new DuplicatePageMessage(expression));
            }
            else
            {
                environment.PageLabels.Add(expression.Id);
            }

            return base.Visit(expression, environment);
        }

        public override object Visit(Section expression, FormCheckerEnvironment environment)
        {
            if (environment.SectionNames.Contains(expression.Name.Value))
            {
                this.Report.Add(new DuplicateSectionNameMessage(expression));
            }
            else
            {
                environment.SectionNames.Add(expression.Name.Value);
            }

            return base.Visit(expression, environment);
        }

        public override object Visit(QuestionRule expression, FormCheckerEnvironment environment)
        {
            if (environment.SectionNames.Contains(expression.Name))
            {
                this.Report.Add(new DuplicateQuestionLabelMessage(expression));
            }
            else
            {
                environment.SectionNames.Add(expression.Name);
            }

            return base.Visit(expression, environment);
        }
    }
}