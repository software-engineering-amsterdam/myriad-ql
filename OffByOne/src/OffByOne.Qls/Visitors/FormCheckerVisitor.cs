namespace OffByOne.Qls.Visitors
{
    using OffByOne.LanguageCore.Checker;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Messages;
    using OffByOne.Qls.Visitors.Base;

    public class FormCheckerVisitor : BaseQlsVisitor<object, FormCheckerContext>
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

        public override object Visit(StyleSheet expression, FormCheckerContext context)
        {
            if (context.StyleSheetNames.Contains(expression.Id))
            {
                this.Report.Add(new DuplicateStyleSheetMesssage(expression));
            }
            else
            {
                context.StyleSheetNames.Add(expression.Id);
            }

            return base.Visit(expression, context);
        }

        public override object Visit(Page expression, FormCheckerContext context)
        {
            if (context.PageLabels.Contains(expression.Id))
            {
                this.Report.Add(new DuplicatePageMessage(expression));
            }
            else
            {
                context.PageLabels.Add(expression.Id);
            }

            return base.Visit(expression, context);
        }

        public override object Visit(Section expression, FormCheckerContext context)
        {
            if (context.SectionNames.Contains(expression.Name.Value))
            {
                this.Report.Add(new DuplicateSectionNameMessage(expression));
            }
            else
            {
                context.SectionNames.Add(expression.Name.Value);
            }

            return base.Visit(expression, context);
        }

        public override object Visit(QuestionRule expression, FormCheckerContext context)
        {
            if (context.SectionNames.Contains(expression.Name))
            {
                this.Report.Add(new DuplicateQuestionLabelMessage(expression));
            }
            else
            {
                context.SectionNames.Add(expression.Name);
            }

            return base.Visit(expression, context);
        }
    }
}
