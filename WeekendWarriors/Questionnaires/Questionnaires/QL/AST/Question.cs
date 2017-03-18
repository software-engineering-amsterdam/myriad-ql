using Questionnaires.Compilation;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;
using System.Collections.Generic;

namespace Questionnaires.QL.AST
{
    public class Question : IStatement
    {
        public Question(string identifier, string body, IType type)
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

        public IType Type
        {
            get;
        }
        
        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            // Nothing to check for a qestion
            return true;
        }
    }
}
