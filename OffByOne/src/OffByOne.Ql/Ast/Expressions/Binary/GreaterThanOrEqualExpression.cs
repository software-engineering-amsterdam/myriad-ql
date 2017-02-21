namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

    public class GreaterThanOrEqualExpression : BinaryExpression
    {
        public GreaterThanOrEqualExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }

        public GreaterThanOrEqualExpression(IList<Expression> expressions)
            : this(expressions[0], expressions[1])
        {
        }
    }
}
