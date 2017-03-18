namespace OffByOne.Ql.Checker.Collectors
{
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Common.Visitors.Base;

    public class DependencyCollector : BaseQlVisitor<object, VisitorTypeEnvironment>
    {
        public DependencyCollector()
        {
            this.Dependencies = new HashSet<string>();
        }

        public ISet<string> Dependencies { get; }

        public void Collect(Statement statement)
        {
            this.Visit(statement, new VisitorTypeEnvironment());
        }

        public void Collect(Expression expression)
        {
            this.Visit(expression, new VisitorTypeEnvironment());
        }

        public override object Visit(VariableExpression expression, VisitorTypeEnvironment environment)
        {
            this.Dependencies.Add(expression.Identifier);
            return default(object);
        }

        public override object Visit(QuestionStatement statement, VisitorTypeEnvironment environment)
        {
            statement.ComputationExpression?.Accept(this, environment);
            return default(object);
        }

        public object Visit(UnaryExpression expression, VisitorTypeEnvironment environment)
        {
            expression.Expression.Accept(this, environment);
            return default(object);
        }

        public object Visit(BinaryExpression expression, VisitorTypeEnvironment environment)
        {
            expression.LeftExpression.Accept(this, environment);
            expression.RightExpression.Accept(this, environment);
            return base.Visit(expression, environment);
        }

        public override object Visit(IfStatement statement, VisitorTypeEnvironment environment)
        {
            var questionStatements = statement.Statements.OfType<QuestionStatement>();
            foreach (var question in questionStatements)
            {
                this.Dependencies.Add(question.Identifier);
            }

            return default(object);
        }
    }
}
