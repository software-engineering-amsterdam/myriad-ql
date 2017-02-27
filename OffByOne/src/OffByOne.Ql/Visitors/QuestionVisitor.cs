namespace OffByOne.Ql.Visitors
{
    using OffByOne.LanguageCore.Checker;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Messages;
    using OffByOne.Ql.Visitors.Base;

    public class QuestionVisitor : BaseQlVisitor<object, QuestionVisitorContext>
    {
        public QuestionVisitor(CheckerReport report)
        {
            this.Report = report;
        }

        public QuestionVisitor()
            : this(new CheckerReport())
        {
        }

        public CheckerReport Report { get; set; }

        public override object Visit(QuestionStatement expression, QuestionVisitorContext context)
        {
            if (context.IsNameDuplicate(expression.Label))
            {
                this.Report.Add(new DuplicateQuestionNameMessage(expression));
            }
            else
            {
                context.AddQuestionName(expression.Label);
            }

            if (context.IsLableDuplicate(expression.Identifier))
            {
                this.Report.Add(new DuplicateQuestionLabelMessage(expression));
            }
            else
            {
                context.AddQuestionLabel(expression.Identifier);
            }

            return base.Visit(expression, context);
        }
    }
}
