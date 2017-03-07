namespace OffByOne.Ql.Checker
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Messages;
    using OffByOne.Ql.Visitors.Base;

    public class QuestionVisitor : BaseQlVisitor<object, QuestionVisitorTypeEnvironment>
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

        public override object Visit(QuestionStatement expression, QuestionVisitorTypeEnvironment context)
        {
            // TODO: change string primitives to StringValue?
            // [...].Value.Value is ugly. Since StringValues replace string primitives,
            // maybe we should replace them in the code too?
            if (context.IsNameDuplicate(expression.Label.Value.Value))
            {
                this.Report.Add(new DuplicateQuestionIdentifierMessage(expression));
            }
            else
            {
                context.AddQuestionName(expression.Label.Value.Value);
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
