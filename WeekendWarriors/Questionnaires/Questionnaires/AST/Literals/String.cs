using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;
using System.Diagnostics;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Value;

namespace Questionnaires.AST.Literals
{
    public class String : IExpression
    {
        public String(string value)
        {
            this.Value = value;
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

        public IValue GetResultType(QLContext context)
        {
            return new StringValue();
        }
    }
}
