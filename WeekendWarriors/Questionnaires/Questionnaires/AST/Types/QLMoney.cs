using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;
using System.Diagnostics;

namespace Questionnaires.AST
{
    public class QLMoney : IQLExpression
    {
        public QLMoney(string value)
        {
            this.StringValue = value;
        }

        public string StringValue { get; }

        public decimal Value { get { return decimal.Parse(StringValue); } }

        public QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Trace.Assert(parameters.Count == 0);

            try
            {
                // Literal is invalid if we cannot parse it to a decimal
                var val = Value;
                return QLType.Money;
            }
            catch (FormatException)
            {
                events.Add(new SemanticAnalysisError(string.Format("Cannot convert literal {0} to money", StringValue)));                
            }
            catch (OverflowException)
            {
                events.Add(new SemanticAnalysisError(string.Format("Value {0} is too large for decimal variable", StringValue)));                
            }

            return null;
        }
    }
}
