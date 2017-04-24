namespace OffByOne.Ql.Ast.Literals.Base
{
    using System.Collections.Generic;
    using OffByOne.Ql.Ast.Expressions;

    public abstract class Literal : Expression
    {
        public override ISet<string> GetDependencies()
        {
            return new HashSet<string>();
        }
    }
}
