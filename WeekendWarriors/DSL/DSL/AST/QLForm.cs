using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{

    public class QLForm : INode
    {
        public QLForm(string identifier, List<INode> statements)
        {
            this.Identifier = identifier;
            Statements = statements;
        }   

        public List<INode> Statements
        {
            get;
        }

        public String Identifier
        {
            get;
        }

        public bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            bool childrenValid = true;
            foreach(var statement in Statements)
            {
                if (!statement.Validate(ref warnings, ref errors))
                    childrenValid = false;
            }

            return childrenValid;
        }
    }
}
