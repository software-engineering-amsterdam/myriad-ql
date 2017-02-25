using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    class DecimalValue : Value<decimal>
    {
        public DecimalValue(decimal value)
        {
            this.Val = value;
        }

        public override decimal AsDecimal()
        {
            return this.Val;
        }
    }
}
