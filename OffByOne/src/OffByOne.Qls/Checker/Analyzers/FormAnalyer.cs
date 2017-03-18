namespace OffByOne.Qls.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker.Analyzers.Contracts;
    using OffByOne.Qls.Checker.Analyzers.Environment;
    using OffByOne.Qls.Checker.Analyzers.Environment.Contracts;
    using OffByOne.Qls.Checker.Messages;
    using OffByOne.Qls.Common.Visitors.Base;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class FormAnalyer : BaseQlsVisitor<object, IFormAnalyzerEnvironment>, IAnalyzer
    {
        public FormAnalyer()
            : this(new CheckerReport())
        {
        }

        public FormAnalyer(ICheckerReport report)
        {
            if (report == null)
            {
                throw new ArgumentNullException(nameof(report));
            }

            this.Report = report;
        }

        public ICheckerReport Report { get; }

        public void Analyze(StyleSheet root, IDictionary<string, ValueType> questionMappings)
        {
            if (root == null)
            {
                throw new ArgumentNullException(nameof(root));
            }

            this.Visit(root, new FormAnalyzerEnvironment());
        }

        public override object Visit(StyleSheet statement, IFormAnalyzerEnvironment environment)
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

        public override object Visit(Page statement, IFormAnalyzerEnvironment environment)
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

        public override object Visit(Section statement, IFormAnalyzerEnvironment environment)
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

        public override object Visit(QuestionRule rule, IFormAnalyzerEnvironment environment)
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