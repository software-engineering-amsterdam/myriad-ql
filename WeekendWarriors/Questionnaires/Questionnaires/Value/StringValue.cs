using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    class StringValue : Value<String>
    {
        public StringValue(String value)
        {
            this.Val = value;
        }

        public override String AsString()
        {
            return this.Val;
        }
    }
}
