using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Compilation;

namespace Questionnaires.QL.AST
{
    public interface INode
    {
        bool CheckSemantics(SemanticAnalysis.QLContext context, List<Compilation.Message> messages);
    }
}
