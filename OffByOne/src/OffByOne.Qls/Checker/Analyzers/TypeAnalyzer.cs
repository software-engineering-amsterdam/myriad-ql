namespace OffByOne.Qls.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Checker.Analyzers.Contracts;
    using OffByOne.Qls.Checker.Analyzers.Environment;
    using OffByOne.Qls.Checker.Analyzers.Environment.Contracts;
    using OffByOne.Qls.Checker.Messages;
    using OffByOne.Qls.Common.Visitors.Base;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class TypeAnalyzer : BaseQlsVisitor<object, ITypeAnalyzerEnvironment>, IAnalyzer
    {
        private IDictionary<string, ValueType> qlQuestionMappings;

        public TypeAnalyzer()
            : this(new CheckerReport())
        {
        }

        public TypeAnalyzer(ICheckerReport report)
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

            if (questionMappings == null)
            {
                throw new ArgumentNullException(nameof(questionMappings));
            }

            this.qlQuestionMappings = questionMappings;
            this.Visit(root, new TypeAnalyzerEnvironment());
        }

        public override object Visit(QuestionRule rule, ITypeAnalyzerEnvironment environment)
        {
            if (!this.qlQuestionMappings.ContainsKey(rule.Identifier))
            {
                this.Report.Add(new UndeclaredQuestionMessage(rule.Identifier));
                return base.Visit(rule, environment);
            }

            var questionType = this.qlQuestionMappings[rule.Identifier];
            this.CheckWidgetTyping(rule.Widget, questionType, environment);

            return base.Visit(rule, environment);
        }

        public override object Visit(ValueTypeRule rule, ITypeAnalyzerEnvironment environment)
        {
            this.CheckWidgetTyping(rule.Widget, rule.ValueType, environment);
            return base.Visit(rule, environment);
        }

        private void CheckWidgetTyping(
            Widget widget,
            ValueType questionType,
            ITypeAnalyzerEnvironment environment)
        {
            if (!environment.IsWidgetTypingCorrect(widget, questionType))
            {
                this.Report.Add(new InvalidWidgetTypeMessage(
                    widget,
                    environment.GetCorrectTypesForWidget(widget),
                    questionType));
            }
        }
    }
}
