using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{

    class QLForm : INode
    {
        public QLForm(string identifier)
        {
            this.Identifier = identifier;
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

            string s = "Form " + Identifier + "\n\r";
            foreach (INode statement in Statements)
            {
                s += statement.ToString(depth+1);
            }
            return s;
            throw new NotImplementedException();
        }

        public List<INode> Statements
        {
            get;
        }

        public String Identifier
        {
            get;
        }
    }
}
