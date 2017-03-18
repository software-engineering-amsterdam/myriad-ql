namespace OffByOne.Qls.Ast.Style.Statements
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Wrappers;

    using OffByOne.Qls.Ast.Style.Statements.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public class StyleSheet : Statement
    {
        public StyleSheet(
            string id,
            IEnumerable<Page> pages = null)
        {
            if (id.IsNullOrWhiteSpace())
            {
                throw new ArgumentException("A non-null, non-empty identifier must be given.");
            }

            this.Id = id;
            this.Pages = pages;
        }

        public string Id { get; }

        public IEnumerable<Page> Pages { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
