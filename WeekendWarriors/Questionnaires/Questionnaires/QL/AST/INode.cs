using Questionnaires.ErrorHandling;
using Questionnaires.QL.SemanticAnalysis;
using System.Collections.Generic;

namespace Questionnaires.QL.AST
{
    public interface INode
    {
        bool CheckSemantics(QLContext context, List<Message> messages);
    }
}
