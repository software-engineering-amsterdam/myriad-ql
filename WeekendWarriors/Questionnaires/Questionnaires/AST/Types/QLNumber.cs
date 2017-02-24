using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace Questionnaires.AST
{
    public class QLNumber : IQLExpression
    {
        public QLNumber(string value)
        {
            this.StringValue = value;
        }

        public string StringValue { get; }        

        public int Value { get { return int.Parse(StringValue); } }

        public QLType? CheckTypes(List<QLType> parameters, QLContext context, List<ISemenaticAnalysisEvent> events)
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
                events.Add(new SemanticAnalysisError(string.Format("Cannot convert literal {0} to number", StringValue)));                
            }
            catch (OverflowException)
            {
                events.Add(new SemanticAnalysisError(string.Format("Value {0} is too large for number variable", StringValue)));                
            }

            return null;
        }
    }
}
