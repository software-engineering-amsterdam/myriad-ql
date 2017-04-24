namespace OffByOne.Qls.Ast.Style.Properties.Base
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public abstract class Property : AstNode, IVisitableProperty
    {
        public abstract TResult Accept<TResult, TContext>(IPropertyVisitor<TResult, TContext> visitor, TContext environment)
            where TContext : IEnvironment;
    }
}
