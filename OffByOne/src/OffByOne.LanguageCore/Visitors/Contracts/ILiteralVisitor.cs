namespace OffByOne.LanguageCore.Visitors.Contracts
{
    using OffByOne.LanguageCore.Ast.Literals;

    public interface ILiteralVisitor<out TResult> : IVisitor
    {
        TResult Visit(IntegerLiteral literal);

        TResult Visit(MoneyLiteral literal);

        TResult Visit(DecimalLiteral literal);

        TResult Visit(BooleanLiteral literal);

        TResult Visit(StringLiteral literal);

        TResult Visit(DateLiteral literal);

        TResult Visit(HexLiteral literal);
    }
}
