namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

    public class EqualExpression : BinaryExpression
    {
        public EqualExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }

        public EqualExpression(IList<Expression> expressions)
            : this(expressions[0], expressions[1])
        {
        }
    }
}
