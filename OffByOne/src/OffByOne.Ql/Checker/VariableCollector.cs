namespace OffByOne.Ql.Checker
{
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Visitors.Base;

    public class VariableCollector : BaseQlVisitor<object, VisitorTypeEnvironment>
    {
        public VariableCollector()
        {
            this.Variables = new HashSet<VariableExpression>();
        }

        public ISet<VariableExpression> Variables { get; set; }

        public IEnumerable<string> VariableIds => this.Variables.Select(x => x.Identifier);

        public void Collect(Expression root)
        {
            this.Visit(root, new VisitorTypeEnvironment());
        }

        public override object Visit(VariableExpression expression, VisitorTypeEnvironment environment)
        {
            this.Variables.Add(expression);
            return base.Visit(expression, environment);
        }
    }
}
