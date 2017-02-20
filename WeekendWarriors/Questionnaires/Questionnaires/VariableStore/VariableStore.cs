using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Questionnaires.VariableStore
{
    class VariableStore : IVariableStore
    {
        protected Dictionary<string, Questionnaires.Value.IValue> variables = new Dictionary<string, Value.IValue>();

        public void SetValue(string name, decimal value)
        {
            variables[name] = new Questionnaires.Value.DecimalValue(value);
        }

        public void SetValue(string name, bool value)
        {
            variables[name] = new Questionnaires.Value.BoolValue(value);
        }

        public void SetValue(string name, int value)
        {
            variables[name] = new Questionnaires.Value.IntValue(value);
        }

        public Questionnaires.Value.IValue GetValue(string name)
        {
            return variables[name];
        }
    }
}
