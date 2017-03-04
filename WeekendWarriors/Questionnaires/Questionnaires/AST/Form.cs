using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;

namespace Questionnaires.AST
{

    public class Form : INode
    {
        public Form(string identifier, List<INode> statements)
        {
            this.Identifier = identifier;
            Statements = statements;
        }   

        public List<INode> Statements
        {
            get;
        }

        public String Identifier
        {
            get;
        }

        public QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<SemanticAnalysis.Messages.Message> events)
        {
            // No type validation to do here.
            return null;
        }
    }
}
