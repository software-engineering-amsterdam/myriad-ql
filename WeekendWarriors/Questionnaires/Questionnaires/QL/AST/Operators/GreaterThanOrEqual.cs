using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class GreaterThanOrEqual : Binary
    {
        public GreaterThanOrEqual(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {
        }


        public override IType GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).GreaterThanOrEqual(Rhs.GetResultType(context));
        }
    }
}
