using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class LessThan : Binary
    {
        public LessThan(IExpression lhs,  IExpression rhs) : base(lhs, rhs)
        {
        }
        

        public override IType GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).LessThan(Rhs.GetResultType(context));
        }
    }
}
