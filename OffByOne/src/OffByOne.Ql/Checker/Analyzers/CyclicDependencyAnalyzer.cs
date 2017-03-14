namespace OffByOne.Ql.Checker.Analyzers
{
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Checker.Analyzers.CircularDependencies;
    using OffByOne.Ql.Checker.Analyzers.Contracts;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Checker.Contracts;
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
            this.Report = report;
            this.circularDependencyChecker = new CircularDependencyChecker();
        }

        public ICheckerReport Report { get; }

        public void Analyze(FormStatement root)
        {
            this.Visit(root, new QuestionVisitorTypeEnvironment());
            var result = this.circularDependencyChecker.CircularDependencies;
        }

        public override object Visit(QuestionStatement expression, QuestionVisitorTypeEnvironment environment)
        {
            if (expression.ComputationExpression != null)
            {
                this.GetVariables(expression.ComputationExpression).ForEach(x => this.circularDependencyChecker
                    .AddDependency(new Dependency(x, expression.Identifier)));
            }

            return base.Visit(expression, environment);
        }

        public override object Visit(IfStatement expression, QuestionVisitorTypeEnvironment environment)
        {
            var conditionVars = this.GetVariables(expression.Condition);
            var ifBodyVars = expression.Statements.SelectMany(this.GetVariables);
            var elseBodyVars = expression.ElseStatements.SelectMany(this.GetVariables);

            this.circularDependencyChecker.AddDependencies(ifBodyVars, conditionVars);
            this.circularDependencyChecker.AddDependencies(elseBodyVars, conditionVars);

            return base.Visit(expression, environment);
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
