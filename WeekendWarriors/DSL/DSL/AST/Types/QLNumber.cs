using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLNumber : INode
    {
        public QLNumber(int value)
        {
            this.Value = value;
        }

        public int Value
        {
            get;
        }
    }
}
