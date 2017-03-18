using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class Division : Binary
    {
        public Division(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {

        }

        public override IType GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).Divide(Rhs.GetResultType(context));
        }
    }
}
