using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    public class BoolValue : Value<bool>
    {
        public BoolValue(bool value)
        {
            this.Val = value;
        }

        public override bool AsBool()
        {
            return this.Val;
        }

        public override IValue Bang()
        {
            return new BoolValue(!this.Val);
        }

        public override IValue And(BoolValue value)
        {
            return new BoolValue(this.Val && value.GetValue());
        }

        public override IValue Or(BoolValue value)
        {
            return new BoolValue(this.Val || value.GetValue());
        }

        public override IValue EqualTo(BoolValue value)
        {
            return new BoolValue(this.Val == value.GetValue());
        }

        public override IValue InequalTo(BoolValue value)
        {
            return new BoolValue(this.Val != value.GetValue());
        }

        
    }
}
