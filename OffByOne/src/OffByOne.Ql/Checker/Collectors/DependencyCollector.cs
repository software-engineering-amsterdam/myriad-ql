namespace OffByOne.Ql.Checker.Collectors
{
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Common.Visitors.Base;

    public class DependencyCollector : BaseQlDfsVisitor<object, TypeEnvironment>
    {
        public DependencyCollector()
        {
            this.Dependencies = new HashSet<string>();
        }

        public ISet<string> Dependencies { get; }

        public void Collect(Statement statement)
        {
            this.Visit(statement, new TypeEnvironment());
        }

        public void Collect(Expression expression)
        {
            this.Visit(expression, new TypeEnvironment());
        }

        public override object Visit(VariableExpression expression, TypeEnvironment environment)
        {
            this.Dependencies.Add(expression.Identifier);
            return default(object);
        }

        public override object Visit(QuestionStatement statement, TypeEnvironment environment)
        {
            statement.ComputationExpression?.Accept(this, environment);
            return default(object);
        }

        public object Visit(UnaryExpression expression, TypeEnvironment environment)
        {
            expression.Expression.Accept(this, environment);
            return default(object);
        }

        public object Visit(BinaryExpression expression, TypeEnvironment environment)
        {
            expression.LeftExpression.Accept(this, environment);
            expression.RightExpression.Accept(this, environment);
            return base.Visit(expression, environment);
        }

        public override object Visit(IfStatement statement, TypeEnvironment environment)
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
