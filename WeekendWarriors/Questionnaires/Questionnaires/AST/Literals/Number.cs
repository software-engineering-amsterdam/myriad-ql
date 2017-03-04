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
    public class Number : IExpression
    {
        public Number(string value)
        {
            this.StringValue = value;
        }

        public string StringValue { get; }        

        public int Value { get { return int.Parse(StringValue); } }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            try
            {                
                var val = Value;
                return true;
            }
            catch (FormatException)
            {
                messages.Add(new Error(string.Format("Cannot convert literal {0} to number", StringValue)));
            }
            catch (OverflowException)
            {
                messages.Add(new Error(string.Format("Value {0} is too large for number variable", StringValue)));
            }
            return false;
        }

        public IValue GetResultType(QLContext context)
        {
            return new IntValue();
        }
    }
}
