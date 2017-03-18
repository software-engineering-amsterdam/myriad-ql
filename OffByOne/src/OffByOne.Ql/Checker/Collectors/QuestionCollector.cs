namespace OffByOne.Ql.Checker.Collectors
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Common.Visitors.Base;

    public class QuestionCollector : BaseQlVisitor<object, QuestionEnvironment>
    {
        public QuestionCollector()
        {
            this.Mappings = new Dictionary<string, ValueType>();
        }

        public IDictionary<string, ValueType> Mappings { get; set; }

        public void Collect(FormStatement root)
        {
            this.Visit(root, new QuestionEnvironment());
        }

        public override object Visit(QuestionStatement statement, QuestionEnvironment environment)
        {
            this.Mappings[statement.Identifier] = statement.Type;
            return base.Visit(statement, environment);
        }
    }
}
