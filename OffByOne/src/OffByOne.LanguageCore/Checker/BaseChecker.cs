namespace OffByOne.LanguageCore.Checker
{
    using System;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.ValueTypes;

    using ValueType = OffByOne.LanguageCore.Ast.ValueTypes.Base.ValueType;

    public class BaseChecker
    {
        public virtual ValueType CheckTypes(AstNode node)
        {
            switch (node)
            {
                case StringLiteral _: return new StringValueType();
                case BooleanLiteral _: return new BooleanValueType();
                case DateLiteral _: return new DateValueType();

                default:
                    throw new ArgumentOutOfRangeException(nameof(node), "Unknow AST node!");
            }
        }
    }
}
