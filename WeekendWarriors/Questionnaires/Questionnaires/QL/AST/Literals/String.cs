using Questionnaires.ErrorHandling;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;
using System.Collections.Generic;

namespace Questionnaires.QL.AST.Literals
{
    public class String : IExpression
    {
        public String(string value)
        {
            Value = value;
        }

        public string Value
        {
            get;
        }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            // Nothing to check for a string literal 
            return true;
        }

        public IType GetResultType(QLContext context)
        {
            return new StringType();
        }
    }
}
