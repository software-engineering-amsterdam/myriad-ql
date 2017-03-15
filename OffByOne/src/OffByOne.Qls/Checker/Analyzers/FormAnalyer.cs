namespace OffByOne.Qls.Checker.Analyzers
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Analyzers.Contracts;
    using OffByOne.Qls.Checker.Analyzers.Environment;
    using OffByOne.Qls.Checker.Messages;
    using OffByOne.Qls.Visitors.Base;

    public class FormAnalyer : BaseQlsVisitor<object, FormAnalyzerEnvironment>, IAnalyzer
    {
        public FormAnalyer()
            : this(new CheckerReport())
        {
        }

        public FormAnalyer(ICheckerReport report)
        {
            this.Report = report;
        }

        public ICheckerReport Report { get; }

        public void Analyze(StyleSheet root, IDictionary<string, ValueType> qlQuestionMappings)
        {
            this.Visit(root, new FormAnalyzerEnvironment());
        }

        public override object Visit(StyleSheet expression, FormAnalyzerEnvironment environment)
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

        public override object Visit(Page expression, FormAnalyzerEnvironment environment)
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

        public override object Visit(Section expression, FormAnalyzerEnvironment environment)
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

        public override object Visit(QuestionRule expression, FormAnalyzerEnvironment environment)
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