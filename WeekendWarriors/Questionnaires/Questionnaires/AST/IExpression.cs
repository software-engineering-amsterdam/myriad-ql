using Questionnaires.SemanticAnalysis;
using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST
{
    public interface IExpression : INode
    {
        IValue GetResultType(QLContext context);
    }
}
