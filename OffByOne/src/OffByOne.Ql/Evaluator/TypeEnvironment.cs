namespace OffByOne.Ql.Evaluator
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;
    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Values.Contracts;
    using OffByOne.Ql.Visitors.Contracts;

    public class TypeEnvironment : IContext
    {
        private readonly IDictionary<string, IValue> values;

        public TypeEnvironment()
        {
            this.values = new Dictionary<string, IValue>();
        }

        public bool AddOrUpdateValue(string key, IValue value)
        {
            if (key.IsNullOrWhiteSpace())
            {
                throw new ArgumentException("The symbol name must not be null or whitespace.", nameof(key));
            }

            if (value == null)
            {
                throw new ArgumentNullException(nameof(key), "Value must not be null.");
            }

            bool updated = false;

            if (this.values.ContainsKey(key))
            {
                updated = this.values[key] != value;
            }

            this.values[key] = value;

            return updated;
        }

        public bool AddOrUpdateValue(string key, Expression value)
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value), "Value expression must be different than null.");
            }

            var evaluator = new Evaluator();
            var computedValue = evaluator.Evaluate(value, this);

            return this.AddOrUpdateValue(key, computedValue);
        }

        public IValue GetValueOf(string key)
        {
            if (key.IsNullOrWhiteSpace())
            {
                throw new ArgumentException("The symbol name must not be null or whitespace.", nameof(key));
            }

            return this.values.GetOrDefault(key);
        }
    }
}
