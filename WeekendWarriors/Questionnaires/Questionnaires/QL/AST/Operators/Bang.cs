using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class Bang : Unary
    {
        public Bang(IExpression operand) : base(operand)
        {

        }

        public override IType GetResultType(QLContext context)
        {
            return Operand.GetResultType(context).Bang();
        }
    }
}
