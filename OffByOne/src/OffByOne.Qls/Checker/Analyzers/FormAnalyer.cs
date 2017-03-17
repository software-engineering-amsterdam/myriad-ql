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

        public override object Visit(StyleSheet statement, FormAnalyzerEnvironment environment)
        {
            if (environment.StyleSheetNames.Contains(statement.Id))
            {
                this.Report.Add(new DuplicateStyleSheetMesssage(statement));
            }
            else
            {
                environment.StyleSheetNames.Add(statement.Id);
            }

            return base.Visit(statement, environment);
        }

        public override object Visit(Page statement, FormAnalyzerEnvironment environment)
        {
            if (environment.PageLabels.Contains(statement.Id))
            {
                this.Report.Add(new DuplicatePageMessage(statement));
            }
            else
            {
                environment.PageLabels.Add(statement.Id);
            }

            return base.Visit(statement, environment);
        }

        public override object Visit(Section statement, FormAnalyzerEnvironment environment)
        {
            if (environment.SectionNames.Contains(statement.Name.Value))
            {
                this.Report.Add(new DuplicateSectionNameMessage(statement));
            }
            else
            {
                environment.SectionNames.Add(statement.Name.Value);
            }

            return base.Visit(statement, environment);
        }

        public override object Visit(QuestionRule rule, FormAnalyzerEnvironment environment)
        {
            if (environment.SectionNames.Contains(rule.Identifier))
            {
                this.Report.Add(new DuplicateQuestionLabelMessage(rule));
            }
            else
            {
                environment.SectionNames.Add(rule.Identifier);
            }

            return base.Visit(rule, environment);
        }
    }
}