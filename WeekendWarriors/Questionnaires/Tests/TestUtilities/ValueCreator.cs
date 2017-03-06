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
        public static IType CreateValue(object value)
        {
            if (value.GetType() == typeof(int))
                return new IntegerType((int)value);
            else if (value.GetType() == typeof(decimal))
                return new MoneyType((decimal)value);
            else if (value.GetType() == typeof(bool))
                return new BooleanType((bool)value);
            else if (value.GetType() == typeof(string))
                return new StringType((string)value);

            throw new NotSupportedException();
        }
    }
}
