using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.Value
{
    class IntValue : Value<int>
    {
        protected bool? Value = null;

        public IntValue(int value)
        {
            this.Val = value;
        }

        public IntValue()
        {
            // Default constructor creates type as null
        }

        public override int? AsInt()
        {
            return this.Val;
        }
    }
}
