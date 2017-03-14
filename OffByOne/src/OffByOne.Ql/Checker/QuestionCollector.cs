namespace OffByOne.Ql.Checker
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Visitors.Base;

    public class QuestionCollector : BaseQlVisitor<object, QuestionVisitorTypeEnvironment>
    {
        public QuestionCollector()
        {
            this.Mappings = new Dictionary<string, ValueType>();
        }

        public IDictionary<string, ValueType> Mappings { get; set; }

        public void Collect(FormStatement root)
        {
            this.Visit(root, new QuestionVisitorTypeEnvironment());
        }

        public override object Visit(QuestionStatement statement, QuestionVisitorTypeEnvironment environment)
        {
            this.Mappings[statement.Identifier] = statement.Type;
            return base.Visit(statement, environment);
        }
    }
}
