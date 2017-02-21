namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

    public class SubtractExpression : BinaryExpression
    {
        public SubtractExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }

        public SubtractExpression(IList<Expression> expressions)
            : this(expressions[0], expressions[1])
        {
        }
    }
}
