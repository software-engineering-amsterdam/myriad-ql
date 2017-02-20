using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST
{
    public class QLBoolean : INode
    {
        public QLBoolean(bool value)
        {
            this.Value = value;
        }

        public bool Value
        {
            get;
        }
    }
}
