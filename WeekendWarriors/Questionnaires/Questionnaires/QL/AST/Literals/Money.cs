using Questionnaires.ErrorHandling;
using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.QL.AST.Types;
using System;
using System.Collections.Generic;
using System.Globalization;

namespace Questionnaires.QL.AST.Literals
{
    public class Money : IExpression
    {
        public string ValueAsString { get; }
        public decimal Value { get { return decimal.Parse(ValueAsString, NumberStyles.Any, CultureInfo.InvariantCulture); } }

        public Money(string value)
        {
            ValueAsString = value;
        }

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
                messages.Add(new Error(string.Format("Cannot convert literal {0} to money", ValueAsString)));
            }
            catch (OverflowException)
            {
                messages.Add(new Error(string.Format("Value {0} is too large for decimal variable", ValueAsString)));
            }

            return false;
        }

        public IType GetResultType(QLContext context)
        {
            return new MoneyType();
        }

        public void GetIdentifiers(HashSet<Identifier> identifiers)
        {
            // No identifiers here
        }
    }
}
