using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;

namespace Questionnaires.AST
{
    public class QLQuestion : INode
    {
        public QLQuestion(string identifier, string body, QLType type)
        {
            this.Identifier = identifier;
            this.Body = body;
            this.Type = type;
        }

        public string Identifier
        {
            get;
        }

        public string Body
        {
            get;
        }

        public QLType Type
        {
            get;
        }
        

        public QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            return Type;
        }
    }
}
