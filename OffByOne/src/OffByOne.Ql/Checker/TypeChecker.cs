namespace OffByOne.Ql.Checker
{
    using System;
    using System.Linq;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Visitors;

    public class TypeChecker
    {
        // TODO: Extract interfaces!
        private readonly TypeVisitor typeVisitor;
        private readonly QuestionVisitor questionVisitor;

        public TypeChecker()
            : this(new TypeVisitor(), new QuestionVisitor())
        {
        }

        public TypeChecker(
            TypeVisitor typeVisitor,
            QuestionVisitor questionVisitor)
        {
            if (typeVisitor == null)
            {
                throw new ArgumentNullException(nameof(typeVisitor), "A valid Type Visitor must provided.");
            }

            if (questionVisitor == null)
            {
                throw new ArgumentNullException(nameof(questionVisitor), "A valid Question Visitor must provided.");
            }

            this.typeVisitor = typeVisitor;
            this.questionVisitor = questionVisitor;
        }

        public CheckerReport Check(FormStatement node)
        {
            this.typeVisitor.Visit(node, new VisitorTypeEnvironment());
            this.questionVisitor.Visit(node, new QuestionVisitorTypeEnvironment());

            var finalReport = new CheckerReport();
            finalReport.Add(this.typeVisitor.Report.AllMessages.ToList());
            finalReport.Add(this.questionVisitor.Report.AllMessages.ToList());

            return finalReport;
        }
    }
}
