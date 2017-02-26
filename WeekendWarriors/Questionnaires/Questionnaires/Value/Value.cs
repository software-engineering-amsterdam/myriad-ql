using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    class Value<T> : IValue
    {
        protected T Val = default(T);

        public virtual int AsInt()
        {
            throw new NotSupportedException();
        }

        public virtual decimal AsDecimal()
        {
            throw new NotSupportedException();
        }

        public virtual bool AsBool()
        {
            throw new NotSupportedException();
        }

        public virtual String AsString()
        {
            throw new NotSupportedException();
        }

        public virtual IValue Add(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Subtract(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Multiply(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Divide(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue And(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue Or(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThan(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue LessThanOrEqual(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThan(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue GreaterThanOrEqual(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue EqualTo(IValue value)
        {
            throw new NotSupportedException();
        }

        public virtual IValue InequalTo(IValue value)
        {
            throw new NotSupportedException();
        }
    }
}
