using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class GreaterThan : Binary
    {
        public GreaterThan(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {
        }


        public override IType GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).GreaterThan(Rhs.GetResultType(context));
        }
    }
}
