namespace OffByOne.Ql.Checker.Collectors
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Common.Visitors.Base;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class QuestionCollector : BaseQlDfsVisitor<object, QuestionEnvironment>
    {
        public QuestionCollector()
        {
            this.Mappings = new Dictionary<string, ValueType>();
        }

        public IDictionary<string, ValueType> Mappings { get; set; }

        public void Collect(FormStatement root)
        {
            if (root == null)
            {
                throw new ArgumentNullException(nameof(root));
            }

            this.Visit(root, new QuestionEnvironment());
        }

        public override object Visit(QuestionStatement statement, QuestionEnvironment environment)
        {
            this.Mappings[statement.Identifier] = statement.Type;
            return base.Visit(statement, environment);
        }
    }
}
