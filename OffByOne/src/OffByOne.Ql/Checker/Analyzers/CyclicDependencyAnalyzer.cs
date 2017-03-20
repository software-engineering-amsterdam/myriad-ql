namespace OffByOne.Ql.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Checker.Analyzers.CircularDependencies;
    using OffByOne.Ql.Checker.Analyzers.Contracts;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Checker.Collectors;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Ql.Checker.Messages;
    using OffByOne.Ql.Common.Visitors.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    public class CyclicDependencyAnalyzer : BaseQlDfsVisitor<object, IEnvironment>, IAnalyzer
    {
        private readonly CircularDependencyChecker circularDependencyChecker;

        public CyclicDependencyAnalyzer()
            : this(new CheckerReport())
        {
        }

        public CyclicDependencyAnalyzer(ICheckerReport report)
        {
            if (report == null)
            {
                throw new ArgumentNullException(nameof(report));
            }

            this.Report = report;
            this.circularDependencyChecker = new CircularDependencyChecker();
        }

        public ICheckerReport Report { get; }

        public void Analyze(FormStatement root)
        {
            if (root == null)
            {
                throw new ArgumentNullException(nameof(root));
            }

            this.Visit(root, new QuestionEnvironment());
            var errors = this.circularDependencyChecker.CircularDependencies;

            errors.ForEach(x => this.Report.Add(new CircularDependencyMessage(x.StartPointId)));
        }

        public override object Visit(QuestionStatement statement, IEnvironment environment)
        {
            if (statement.ComputationExpression != null)
            {
                this.GetVariables(statement.ComputationExpression).ForEach(x => this.circularDependencyChecker
                    .AddDependency(new Dependency(statement.Identifier, x)));
            }

            return base.Visit(statement, environment);
        }

        public override object Visit(IfStatement statement, IEnvironment environment)
        {
            var conditionVars = this.GetVariables(statement.Condition);
            var bodyVars = this.GetVariables(statement);

            this.circularDependencyChecker.AddDependencies(bodyVars, conditionVars);

            return base.Visit(statement, environment);
        }

        private ISet<string> GetVariables(Expression expression)
        {
            var variableCollector = new DependencyCollector();
            variableCollector.Collect(expression);
            return variableCollector.Dependencies;
        }

        private ISet<string> GetVariables(Statement statement)
        {
            var variableCollector = new DependencyCollector();
            variableCollector.Collect(statement);
            return variableCollector.Dependencies;
        }
    }
}
