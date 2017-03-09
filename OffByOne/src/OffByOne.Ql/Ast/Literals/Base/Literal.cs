namespace OffByOne.Ql.Ast.Literals.Base
{
    using System;
    using System.Collections.Generic;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class Literal : Expression
    {
        public override ISet<string> GetDependencies()
        {
            return new SortedSet<string>();
        }
    }
}
