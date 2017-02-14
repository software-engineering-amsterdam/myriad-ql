using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLMoney : INode
    {
        public QLMoney(decimal value)
        {
            this.Value = value;
        }

        public decimal Value
        {
            get;
        }
    }
}
