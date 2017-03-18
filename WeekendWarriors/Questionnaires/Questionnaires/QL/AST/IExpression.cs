using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QL.AST
{
    public interface IExpression : INode
    {
        IType GetResultType(QLContext context);
    }
}
