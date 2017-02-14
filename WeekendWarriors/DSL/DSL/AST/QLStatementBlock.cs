using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    class QLStatementBlock : INode
    {
        public QLStatementBlock()
        {
            Statements = new List<INode>();
        }

        public void AddStatement(INode statement)
        {
            Statements.Add(statement);
        }

        public List<INode> Statements
        {
            get;
        }
    }
}
