using System;
using System.Collections.Generic;
using Questionnaires.SemanticAnalysis.Messages;
using System.Globalization;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Value;

namespace Questionnaires.QL.AST.Literals
{
    public class Money : IExpression
    {
        public Money(string value)
        {
            this.StringType = value;
        }

        public string StringType { get; }

        public decimal Value { get { return decimal.Parse(StringType, NumberStyles.Any, CultureInfo.InvariantCulture); } }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            try
            {
                // Literal is invalid if we cannot parse it to a decimal
                var val = Value;
                return true;
            }
            catch (FormatException)
            {
                messages.Add(new Error(string.Format("Cannot convert literal {0} to money", StringType)));
            }
            catch (OverflowException)
            {
                messages.Add(new Error(string.Format("Value {0} is too large for decimal variable", StringType)));
            }

            return false;
        }

        public IType GetResultType(QLContext context)
        {
            return new MoneyType();
        }
    }
}
