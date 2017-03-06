using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;

namespace Questionnaires.QL.AST
{
    public interface INode
    {
        bool CheckSemantics(SemanticAnalysis.QLContext context, List<SemanticAnalysis.Messages.Message> messages);
    }
}
