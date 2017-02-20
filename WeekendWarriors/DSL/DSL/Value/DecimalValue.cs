using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.Value
{
    class DecimalValue : Value<decimal>
    {
        public DecimalValue(decimal value)
        {
            this.Val = value;
        }

        public DecimalValue()
        {
            // Default constructor creates type as null
        }

        public override decimal? AsDecimal()
        {
            return this.Val;
        }
    }
}
