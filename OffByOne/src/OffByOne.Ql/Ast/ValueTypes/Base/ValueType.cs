namespace OffByOne.Ql.Ast.ValueTypes.Base
{
    using System;

    using OffByOne.Ql.Visitors.Contracts;

    public abstract class ValueType : AstNode, IVisitableValueType, IEquatable<ValueType>
    {
        public static bool operator ==(ValueType typeOne, ValueType typeTwo)
        {
            if (object.ReferenceEquals(typeOne, null))
            {
                return object.ReferenceEquals(typeTwo, null);
            }

            return typeOne.Equals(typeTwo);
        }

        public static bool operator !=(ValueType typeOne, ValueType typeTwo)
        {
            return !(typeOne == typeTwo);
        }

        public abstract TResult Accept<TResult, TContext>(
            IValueTypeVisitor<TResult, TContext> visitor,
            TContext context)
            where TContext : IContext;

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override bool Equals(object obj)
        {
            return obj is ValueType;
        }

        public abstract bool Equals(ValueType obj);
    }
}
