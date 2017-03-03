using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tests.QL.Value
{
    class ValueCreator
    {
        public static IValue CreateValue(object value)
        {
            if (value.GetType() == typeof(int))
                return new IntValue((int)value);
            else if (value.GetType() == typeof(decimal))
                return new DecimalValue((decimal)value);
            else if (value.GetType() == typeof(bool))
                return new BoolValue((bool)value);
            else if (value.GetType() == typeof(string))
                return new StringValue((string)value);

            throw new NotSupportedException();
        }
    }
}
