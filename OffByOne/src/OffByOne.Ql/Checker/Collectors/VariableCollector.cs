namespace OffByOne.Ql.Checker.Collectors
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Visitors.Base;

    public class VariableCollector : BaseQlVisitor<object, VisitorTypeEnvironment>
    {
        public VariableCollector()
        {
            this.Variables = new HashSet<string>();
        }

        public ISet<string> Variables { get; }

        public void Collect(Expression root)
        {
            this.Visit(root, new VisitorTypeEnvironment());
        }

        public void Collect(Statement root)
        {
            this.Visit(root, new VisitorTypeEnvironment());
        }

        public override object Visit(VariableExpression expression, VisitorTypeEnvironment environment)
        {
            this.Variables.Add(expression.Identifier);
            return base.Visit(expression, environment);
        }

        public override object Visit(QuestionStatement statement, VisitorTypeEnvironment environment)
        {
            this.Variables.Add(statement.Identifier);
            return base.Visit(statement, environment);
        }
    }
}
