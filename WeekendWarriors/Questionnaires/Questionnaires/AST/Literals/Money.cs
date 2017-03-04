using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages;
using System.Diagnostics;
using System.Globalization;

namespace Questionnaires.AST.Literals
{
    public class Money : IExpression
    {
        public Money(string value)
        {
            this.StringValue = value;
        }

        public string StringValue { get; }

        public decimal Value { get { return decimal.Parse(StringValue, NumberStyles.Any, CultureInfo.InvariantCulture); } }

        public QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<SemanticAnalysis.Messages.Message> events)
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
                events.Add(new Error(string.Format("Cannot convert literal {0} to money", StringValue)));                
            }
            catch (OverflowException)
            {
                events.Add(new Error(string.Format("Value {0} is too large for decimal variable", StringValue)));                
            }

            return null;
        }
    }
}
