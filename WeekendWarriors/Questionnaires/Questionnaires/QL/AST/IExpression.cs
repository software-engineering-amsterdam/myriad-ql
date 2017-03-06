using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QL.AST
{
    public interface IExpression : INode
    {
        IType GetResultType(QLContext context);
    }
}
