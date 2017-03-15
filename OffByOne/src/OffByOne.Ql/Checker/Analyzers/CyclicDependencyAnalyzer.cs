namespace OffByOne.Ql.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

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
    using OffByOne.Ql.Visitors.Base;

    public class CyclicDependencyAnalyzer : BaseQlVisitor<object, QuestionVisitorTypeEnvironment>, IAnalyzer
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
            this.Visit(root, new QuestionVisitorTypeEnvironment());
            var errors = this.circularDependencyChecker.CircularDependencies;

            errors.ForEach(x => this.Report.Add(new CircularDependencyMessage(x.StartPointId)));
        }

        public override object Visit(QuestionStatement statement, QuestionVisitorTypeEnvironment environment)
        {
            if (statement.ComputationExpression != null)
            {
                this.GetVariables(statement.ComputationExpression).ForEach(x => this.circularDependencyChecker
                    .AddDependency(new Dependency(x, statement.Identifier)));
            }

            return base.Visit(statement, environment);
        }

        public override object Visit(IfStatement statement, QuestionVisitorTypeEnvironment environment)
        {
            var conditionVars = this.GetVariables(statement.Condition);
            var ifBodyVars = statement.Statements.SelectMany(this.GetVariables);
            var elseBodyVars = statement.ElseStatements.SelectMany(this.GetVariables);

            this.circularDependencyChecker.AddDependencies(ifBodyVars, conditionVars);
            this.circularDependencyChecker.AddDependencies(elseBodyVars, conditionVars);

            return base.Visit(statement, environment);
        }

        private ISet<string> GetVariables(Expression expression)
        {
            var variableCollector = new VariableCollector();
            variableCollector.Collect(expression);
            return variableCollector.Variables;
        }

        private ISet<string> GetVariables(Statement statement)
        {
            var variableCollector = new VariableCollector();
            variableCollector.Collect(statement);
            return variableCollector.Variables;
        }
    }
}
