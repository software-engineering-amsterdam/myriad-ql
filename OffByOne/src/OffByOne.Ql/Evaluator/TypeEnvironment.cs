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

        public void AddValue(string key, IValue value)
        {
            if (this.values.ContainsKey(key))
            {
                var oldValue = this.values[key];

                if (oldValue == value)
                {
                    return;
                }

                this.values[key] = value;
            }
            else
            {
                this.values[key] = value;
            }
        }

        public void AddValue(string key, Expression value)
        {
            var evaluator = new Evaluator();
            var computedValue = evaluator.Evaluate(value, this);

            this.AddValue(key, computedValue);
        }

        public IValue GetValueOf(string key)
        {
            return this.values.GetOrDefault(key);
        }
    }
}
