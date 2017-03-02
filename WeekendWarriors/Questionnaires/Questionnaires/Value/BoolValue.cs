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

        public override IValue And(IValue value)
        {
            return And((dynamic)value);
        }

        public override IValue Or(IValue value)
        {
            return Or((dynamic)value);
        }

        public override IValue EqualTo(IValue value)
        {
            return EqualTo((dynamic)value);
        }

        public override IValue InequalTo(IValue value)
        {
            return InequalTo((dynamic)value);
        }

        public override IValue Bang()
        {
            return new BoolValue(!this.Val);
        }

        public override IValue And(BoolValue value)
        {
            return new BoolValue(this.Val && value.GetValue());
        }
        public override IValue And(StringValue value)
        {
            return base.And(value);
        }
        public override IValue And(IntValue value)
        {
            return base.And(value);
        }
        public override IValue And(DecimalValue value)
        {
            return base.And(value);
        }

        public override IValue Or(BoolValue value)
        {
            return new BoolValue(this.Val || value.GetValue());
        }
        public override IValue Or(StringValue value)
        {
            return base.Or(value);
        }
        public override IValue Or(IntValue value)
        {
            return base.And(value);
        }
        public override IValue Or(DecimalValue value)
        {
            return base.And(value);
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
