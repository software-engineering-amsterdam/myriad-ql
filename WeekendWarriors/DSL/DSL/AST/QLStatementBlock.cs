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

        public string ToString(uint depth)
        {
            string toPrint = "";

            for (int i = 0; i < depth; i++)
                toPrint += "\t";

            toPrint += "Statements" + "\n\r";

            foreach (var statement in Statements)
                toPrint += statement.ToString(depth + 1);

            return toPrint;
        }

        public List<INode> Statements
        {
            get;
        }
    }
}
