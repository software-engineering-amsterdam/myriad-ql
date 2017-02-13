using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    class QLQuestion : INode
    {
        public QLQuestion(string identifier, string body, string type)
        {
            this.Identifier = identifier;
            this.Body = body;
            this.Type = type;
        }

        public string ToString(uint depth)
        {
            string toPrint = "";

            for (int i = 0; i < depth; i++)
                toPrint += "\t";

            return toPrint + "Question " + Identifier + " " + Body + " " + Type + "\n\r";
        }

        public string Identifier
        {
            get;
        }

        public string Body
        {
            get;
        }

        public string Type
        {
            get;
        }
    }
}
