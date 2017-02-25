using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    class IntValue : Value<int>
    {
        public IntValue(int value)
        {
            this.Val = value;
        }

        public override int AsInt()
        {
            return this.Val;
        }
    }
}
