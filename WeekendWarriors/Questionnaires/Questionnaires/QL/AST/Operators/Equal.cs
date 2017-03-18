using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class Equal : Binary
    {
        public Equal(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {
        }

        public override IType GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).EqualTo(Rhs.GetResultType(context));
        }
    }
}
