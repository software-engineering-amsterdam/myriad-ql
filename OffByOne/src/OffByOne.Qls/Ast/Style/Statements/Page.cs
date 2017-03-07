namespace OffByOne.Qls.Ast.Style.Statements
{
    using System.Collections.Generic;

    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements.Base;
    using OffByOne.Qls.Visitors.Contracts;

    public class Page : Statement
    {
        public Page(
            string id,
            IEnumerable<Section> sections,
            IEnumerable<ValueTypeRule> valueTypeRules)
        {
            this.Id = id;
            this.Sections = sections;
            this.ValueTypeRules = valueTypeRules;
        }

        public string Id { get; private set; }

        public IEnumerable<Section> Sections { get; }

        public IEnumerable<ValueTypeRule> ValueTypeRules { get; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
