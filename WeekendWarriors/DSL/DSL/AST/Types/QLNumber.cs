using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLNumber : IQLExpression
    {
        public QLNumber(string value)
        {
            this.StringValue = value;
        }

        public string StringValue { get; }        

        public int Value { get { return int.Parse(StringValue); } }

        public bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            try
            {
                // Literal is invalid if we cannot parse it to an integer
                var val = Value;
                return true;
            }
            catch (FormatException)
            {
                errors.Add(string.Format("Cannot convert literal {0} to number", StringValue));
                return false;
            }
            catch (OverflowException)
            {
                errors.Add(string.Format("Value {0} is too large for number variable", StringValue));
                return false;
            }
        }

        QLType? IQLExpression.GetQLType()
        {
            return QLType.Number;
        }
    }
}
