using Questionnaires.ErrorHandling;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;
using System.Collections.Generic;

namespace Questionnaires.QL.AST
{
    public class Question : IStatement
    {
        public Question(string identifier, string body, IType type)
        {
            Identifier = identifier;
            Body = body;
            Type = type;
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
            // Nothing to check for a question
            return true;
        }
    }
}
