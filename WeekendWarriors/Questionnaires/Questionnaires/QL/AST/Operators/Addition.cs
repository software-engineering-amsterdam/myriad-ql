using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class Addition : Binary
    {
        public Addition(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {
        }
        
        public override IType GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).Add(Rhs.GetResultType(context));
        }
    }
}
