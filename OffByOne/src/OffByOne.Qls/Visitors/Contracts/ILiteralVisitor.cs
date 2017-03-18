namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Literals;

    public interface ILiteralVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(IntegerLiteral literal, TEnvironment environment);

        TResult Visit(DecimalLiteral literal, TEnvironment environment);

        TResult Visit(StringLiteral literal, TEnvironment environment);

        TResult Visit(HexLiteral literal, TEnvironment environment);
    }
}
