namespace OffByOne.LanguageCore.Checker
{
    using System;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.ValueTypes;

    using ValueType = OffByOne.LanguageCore.Ast.ValueTypes.Base.ValueType;

    public abstract class BaseChecker
    {
        protected BaseChecker()
        {
            this.Report = new CheckerReport();
        }

        protected CheckerReport Report { get; set; }

        public virtual ValueType CheckTypes(AstNode node)
        {
            switch (node)
            {
                case StringLiteral _: return new StringValueType();
                case BooleanLiteral _: return new BooleanValueType();
                case DateLiteral _: return new DateValueType();
                case DecimalLiteral _: return new FloatValueType();
                case IntegerLiteral _: return new IntegerValueType();
                case MoneyLiteral _: return new MoneyValueType();
                case HexLiteral _: return new StringValueType();

                default:
                    throw new ArgumentOutOfRangeException(nameof(node), "Unknow AST node!");
            }
        }
    }
}
