using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    public class StringValue : Value<String>
    {
        public StringValue()
        {
        }

        public StringValue(String value)
        {
            this.Val = value;
        }

        public override BoolValue EqualTo(StringValue value)
        {
            return new BoolValue(this.Val == value.GetValue());
        }

        public override BoolValue InequalTo(StringValue value)
        {
            return new BoolValue(this.Val != value.GetValue());
        }
    }
}
