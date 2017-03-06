using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Questionnaires.VariableStore
{
    public class VariableStore
    {
        private Dictionary<string, Value.IType> variables = new Dictionary<string, Value.IType>();

        public void SetValue(string name, IType value)
        {            
            bool changed = (!variables.ContainsKey(name) || value.InequalTo((dynamic)variables[name]).GetValue());
            variables[name] = value;

            if (changed)
                OnVariableChanged(name, value);
        }

        public void RemoveValue(string name)
        {
            variables.Remove(name);
        }

        public Questionnaires.Value.IType GetValue(string name)
        {
            return variables[name];
        }

        public delegate void VariableChangedEventHandler(object sender, VariableChangedEventArgs arg);
        public event VariableChangedEventHandler VariableChanged;
        public void OnVariableChanged(string name, IType value)
        {
            if (VariableChanged != null)
                VariableChanged(this, new VariableChangedEventArgs(name, value));
        }        
    }
}
