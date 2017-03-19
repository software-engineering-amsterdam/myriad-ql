using Questionnaires.ErrorHandling;
using System.Collections.Generic;

namespace Questionnaires.QL.AST
{
    public interface IStatement : INode
    {
        void GetDependencies(Dictionary<Question, HashSet<Identifier>> dependencies);
    }
}
