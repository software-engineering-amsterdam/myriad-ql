using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Types
{
    public abstract class Value<T> : IType
    {
        protected T Val = default(T);

        public T GetValue()
        {
            return Val;
        }

        public virtual IType Positive()
        {
            throw new NotSupportedException();
        }

        public virtual IType Negative()
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType Bang()
        {
            throw new NotSupportedException();
        }

        public virtual IType Add(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Subtract(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Multiply(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Divide(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType And(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType Or(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType LessThan(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType LessThanOrEqual(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType GreaterThan(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType GreaterThanOrEqual(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType EqualTo(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType InequalTo(StringType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Add(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Subtract(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Multiply(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Divide(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType And(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType Or(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType LessThan(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType LessThanOrEqual(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType GreaterThan(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType GreaterThanOrEqual(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType EqualTo(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType InequalTo(IntegerType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Add(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Subtract(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Multiply(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Divide(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType And(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType Or(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType LessThan(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType LessThanOrEqual(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType GreaterThan(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType GreaterThanOrEqual(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType EqualTo(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType InequalTo(MoneyType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Add(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Subtract(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Multiply(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual IType Divide(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType And(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType Or(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType LessThan(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType LessThanOrEqual(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType GreaterThan(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType GreaterThanOrEqual(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType EqualTo(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public virtual BooleanType InequalTo(BooleanType value)
        {
            throw new NotSupportedException();
        }

        public IType Add(IType value)
        {
            return Add((dynamic)value);
        }

        public IType Subtract(IType value)
        {
            return Subtract((dynamic)value);
        }

        public IType Multiply(IType value)
        {
            return Multiply((dynamic)value);
        }

        public IType Divide(IType value)
        {
            return Divide((dynamic)value);
        }

        public BooleanType And(IType value)
        {
            return And((dynamic)value);
        }

        public BooleanType Or(IType value)
        {
            return Or((dynamic)value);
        }

        public BooleanType LessThan(IType value)
        {
            return LessThan((dynamic)value);
        }

        public BooleanType LessThanOrEqual(IType value)
        {
            return LessThanOrEqual((dynamic)value);
        }

        public BooleanType GreaterThan(IType value)
        {
            return GreaterThan((dynamic)value);
        }

        public BooleanType GreaterThanOrEqual(IType value)
        {
            return GreaterThanOrEqual((dynamic)value);
        }

        public BooleanType EqualTo(IType value)
        {
            return EqualTo((dynamic)value);
        }

        public BooleanType InequalTo(IType value)
        {
            return InequalTo((dynamic)value);
        }
    }
}
