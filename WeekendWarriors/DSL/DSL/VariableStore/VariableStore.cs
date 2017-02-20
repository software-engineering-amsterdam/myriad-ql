using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace DSL.VariableStore
{
    class VariableStore : IVariableStore
    {
        protected Dictionary<string, DSL.Value.IValue> variables = new Dictionary<string, Value.IValue>();

        public void SetValue(string name, decimal value)
        {
            variables[name] = new DSL.Value.DecimalValue(value);
        }

        public void SetValue(string name, bool value)
        {
            variables[name] = new DSL.Value.BoolValue(value);
        }

        public void SetValue(string name, int value)
        {
            variables[name] = new DSL.Value.IntValue(value);
        }

        public DSL.Value.IValue GetValue(string name)
        {
            return variables[name];
        }
    }
}
