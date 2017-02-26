using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Questionnaires.VariableStore
{
    class VariableStore : IVariableStore
    {
        protected Dictionary<string, Value.IValue> variables = new Dictionary<string, Value.IValue>();

        public void SetValue(string name, decimal value)
        {
            variables[name] = new Value.DecimalValue(value);
        }

        public void SetValue(string name, bool value)
        {
            variables[name] = new Value.BoolValue(value);
        }

        public void SetValue(string name, int value)
        {
            variables[name] = new Value.IntValue(value);
        }

        public void SetValue(string name, string value)
        {
            variables[name] = new Value.StringValue(value);
        }

        public void RemoveValue(string name)
        {
            variables.Remove(name);
        }

        public Questionnaires.Value.IValue GetValue(string name)
        {
            return variables[name];
        }
    }
}
