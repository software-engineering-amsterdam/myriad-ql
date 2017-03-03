using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.VariableStore
{
    public class VariableChangedEventArgs
    {
        public VariableChangedEventArgs(string name, IValue value)
        {
            this.Name = name;
            this.Value = value;
        }

        public string Name
        {
            get;
            set;
        }

        public IValue Value
        {
            get;
            set;
        }
    }
}
