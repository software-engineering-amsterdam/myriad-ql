using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Types
{
    public class StringType : Value<String>
    {
        public StringType()
        {
        }

        public StringType(String value)
        {
            this.Val = value;
        }

        public override BooleanType EqualTo(StringType value)
        {
            return new BooleanType(this.Val == value.GetValue());
        }

        public override BooleanType InequalTo(StringType value)
        {
            return new BooleanType(this.Val != value.GetValue());
        }
    }
}
