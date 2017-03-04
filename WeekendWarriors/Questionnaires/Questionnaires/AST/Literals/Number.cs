using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;
using System.Diagnostics;

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

        public QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<SemanticAnalysis.Messages.Message> events)
        {
            Trace.Assert(parameters.Count == 0);

            try
            {
                // Literal is invalid if we cannot parse it to an integer
                var val = Value;
                return QLType.Number;
            }
            catch (FormatException)
            {
                events.Add(new Error(string.Format("Cannot convert literal {0} to number", StringValue)));                
            }
            catch (OverflowException)
            {
                events.Add(new Error(string.Format("Value {0} is too large for number variable", StringValue)));                
            }

            return null;
        }
    }
}
