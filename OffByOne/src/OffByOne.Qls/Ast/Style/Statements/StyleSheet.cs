namespace OffByOne.Qls.Ast.Style.Statements
{
    using System.Collections.Generic;

    using OffByOne.Qls.Ast.Style.Statements.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public class StyleSheet : Statement
    {
        public StyleSheet(
            string id,
            ICollection<Page> pages)
        {
            this.Id = id;
            this.Pages = pages;
        }

        public string Id { get; }

        public ICollection<Page> Pages { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
