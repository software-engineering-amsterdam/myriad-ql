namespace OffByOne.Ql.Checker
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;
    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Visitors.Contracts;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class VisitorTypeEnvironment : IVisitorContext
    {
        private readonly IDictionary<string, ValueType> symbolMappings;

        public VisitorTypeEnvironment()
        {
            this.symbolMappings = new Dictionary<string, ValueType>();
        }

        public void AddSymbol(string name, ValueType type)
        {
            if (name.IsNullOrWhiteSpace())
            {
                throw new ArgumentNullException(nameof(name));
            }

            if (type == null)
            {
                throw new ArgumentNullException(nameof(type));
            }

            // TODO: What about redeclaration?
            if (!this.symbolMappings.ContainsKey(name))
            {
                this.symbolMappings[name] = type;
            }
        }

        public ValueType GetTypeOf(string name)
        {
            if (name.IsNullOrWhiteSpace())
            {
                throw new ArgumentNullException(nameof(name));
            }

            return this.symbolMappings.GetOrDefault(name);
        }
    }
}
