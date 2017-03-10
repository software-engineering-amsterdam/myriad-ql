namespace OffByOne.Qls.Ast.Style.Literals.Base
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Visitors.Contracts;

    public abstract class Literal : AstNode
    {
        public abstract TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
            where TContext : IEnvironment;
    }
}
