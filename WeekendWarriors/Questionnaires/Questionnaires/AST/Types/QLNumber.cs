using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST
{
    public class QLNumber : INode
    {
        public QLNumber(string value)
        {
            this.StringValue = value;
        }

        public string StringValue { get; }
        public int Value { get { return int.Parse(StringValue); } }
    }
}
