using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.QL.AST.Types;
using System.Collections.Generic;

namespace Questionnaires.QL.AST
{
    public interface IExpression : INode
    {
        IType GetResultType(QLContext context);
        void GetIdentifiers(HashSet<Identifier> identifiers);
    }
}
