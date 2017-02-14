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
