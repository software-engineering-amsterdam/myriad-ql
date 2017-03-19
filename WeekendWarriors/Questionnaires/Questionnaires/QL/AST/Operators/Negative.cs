using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class Negative : Unary
    {
        public Negative(IExpression operand) : base(operand)
        {
        }

        public override IType GetResultType(QLContext context)
        {
            return Operand.GetResultType(context).Negative();
        }
    }
}
