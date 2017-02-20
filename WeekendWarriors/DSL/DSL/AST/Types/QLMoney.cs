using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLMoney : IQLExpression
    {
        public QLMoney(string value)
        {
            this.StringValue = value;
        }

        public string StringValue { get; }

        public decimal Value { get { return decimal.Parse(StringValue); } }

        public bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            try
            {
                // Literal is invalid if we cannot parse it to a decimal
                var val = Value;
                return true;
            }
            catch (FormatException)
            {
                errors.Add(string.Format("Cannot convert literal {0} to money", StringValue));
                return false;
            }
            catch (OverflowException)
            {
                errors.Add(string.Format("Value {0} is too large for decimal variable", StringValue));
                return false;
            }
        }

        QLType? IQLExpression.GetQLType()
        {
            return QLType.Money;
        }
    }
}
