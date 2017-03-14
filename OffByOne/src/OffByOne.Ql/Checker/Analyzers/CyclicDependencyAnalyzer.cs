namespace OffByOne.Ql.Checker.Analyzers
{
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Analyzers.Contracts;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Ql.Visitors.Base;

    public class CyclicDependencyAnalyzer : BaseQlVisitor<object, QuestionVisitorTypeEnvironment>, IAnalyzer
    {
        private readonly ISet<string> variableIdentifiers;

        public CyclicDependencyAnalyzer()
            : this(new CheckerReport())
        {
        }

        public CyclicDependencyAnalyzer(ICheckerReport report)
        {
            this.Report = report;
            this.variableIdentifiers = new HashSet<string>();
        }

        public ICheckerReport Report { get; }

        public void Analyze(FormStatement root)
        {
        }

        public override object Visit(QuestionStatement expression, QuestionVisitorTypeEnvironment environment)
        {
            if (expression.ComputationExpression != null)
            {
                var variableCollector = new VariableCollector();
                variableCollector.Collect(expression.ComputationExpression);
                variableCollector.VariableIds.ForEach(x => this.variableIdentifiers.Add(x));
            }

            return base.Visit(expression, environment);
        }

        public override object Visit(IfStatement expression, QuestionVisitorTypeEnvironment environment)
        {
            if (expression.Condition != null)
            {
                var variableCollector = new VariableCollector();
                variableCollector.Collect(expression.Condition);
                variableCollector.VariableIds.ForEach(x => this.variableIdentifiers.Add(x));
            }

            return base.Visit(expression, environment);
        }
    }
}
