using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    class BoolValue : Value<bool>
    {
        public BoolValue(bool value)
        {
            this.Val = value;
        }

        public override bool AsBool()
        {
            return this.Val;
        }

        public IValue And(BoolValue value)
        {
            return new BoolValue(this.Val && value.AsBool());
        }

        public IValue Or(BoolValue value)
        {
            return new BoolValue(this.Val || value.AsBool());
        }

        public IValue EqualTo(BoolValue value)
        {
            return new BoolValue(this.Val == value.AsBool());
        }

        public IValue InequalTo(BoolValue value)
        {
            return new BoolValue(this.Val != value.AsBool());
        }
    }
}
