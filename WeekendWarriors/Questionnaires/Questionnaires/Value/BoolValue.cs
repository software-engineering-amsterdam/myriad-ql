using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    public class BoolValue : Value<bool>
    {
        public BoolValue()
        {
        }

        public BoolValue(bool value)
        {
            this.Val = value;
        }

        public override BoolValue Bang()
        {
            return new BoolValue(!this.Val);
        }

        public override BoolValue And(BoolValue value)
        {
            return new BoolValue(this.Val && value.GetValue());
        }

        public override BoolValue Or(BoolValue value)
        {
            return new BoolValue(this.Val || value.GetValue());
        }

        public override BoolValue EqualTo(BoolValue value)
        {
            return new BoolValue(this.Val == value.GetValue());
        }

        public override BoolValue InequalTo(BoolValue value)
        {
            return new BoolValue(this.Val != value.GetValue());
        }

        
    }
}
