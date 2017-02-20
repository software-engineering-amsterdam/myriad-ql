using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.AST
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
    }
}
