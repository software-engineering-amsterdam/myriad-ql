using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;

namespace Questionnaires.QL.AST
{
    public interface IExpression : INode
    {
        IType GetResultType(QLContext context);
    }
}
