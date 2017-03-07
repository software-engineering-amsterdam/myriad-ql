namespace OffByOne.Ql.Evaluator
{
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;

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
            bool updated = !(this.values.ContainsKey(key) && this.values[key] == value);

            if (this.values.ContainsKey(key))
            {
                updated = this.values[key] != value;
            }

            this.values[key] = value;
            return updated;
        }

        public bool AddOrUpdateValue(string key, Expression value)
        {
            var evaluator = new Evaluator();
            var computedValue = evaluator.Evaluate(value, this);

            return this.AddOrUpdateValue(key, computedValue);
        }

        public IValue GetValueOf(string key)
        {
            return this.values.GetOrDefault(key);
        }
    }
}
