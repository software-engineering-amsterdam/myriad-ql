using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST
{
    public class QLString : INode
    {
        public QLString(string value)
        {
            this.Value = value;
        }

        public string Value
        {
            get;
        }
    }
}
