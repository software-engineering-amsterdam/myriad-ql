using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.Value
{
    class BoolValue : Value<bool>
    {
        protected bool? Value = null;

        public BoolValue(bool value)
        {
            this.Val = value;
        }

        public BoolValue()
        {
            // Default constructor creates type as null
        }

        public override bool? AsBool()
        {
            return this.Val;
        }
    }
}
