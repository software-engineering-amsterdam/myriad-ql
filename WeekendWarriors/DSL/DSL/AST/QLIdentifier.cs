using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLIdentifier : INode
    {
        public QLIdentifier(string name)
        {
            this.Name = name;
        }

        public string Name
        {
            get;
        }
    }
}
