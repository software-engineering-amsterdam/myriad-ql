using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST
{
    public class QLMoney : INode
    {
        public QLMoney(string value)
        {
            this.StringValue = value;
        }

        public string StringValue { get; }
        public decimal Value { get { return decimal.Parse(StringValue); } }
    }
}
