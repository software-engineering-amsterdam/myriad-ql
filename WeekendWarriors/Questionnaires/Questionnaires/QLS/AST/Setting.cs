using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QLS.AST
{
    class Setting : INode
    {
        public string Key { get; }
        public string Value { get; }
        public Setting(string key, string value)
        {
            this.Key = key;
            this.Value = value;
        }
    }
}
