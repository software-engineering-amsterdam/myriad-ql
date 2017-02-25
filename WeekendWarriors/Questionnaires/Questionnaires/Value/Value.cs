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
    }
}
