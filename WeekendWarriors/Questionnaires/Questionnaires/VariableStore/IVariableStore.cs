using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.VariableStore
{
    interface IVariableStore
    {
        void SetValue(string name, int value);
        void SetValue(string name, bool value);
        void SetValue(string name, decimal value);
        void SetValue(string name, string value);
        void SetValue(string name, IValue value);

        void RemoveValue(string name);
    };
}
