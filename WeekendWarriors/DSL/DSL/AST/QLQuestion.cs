using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    public class QLQuestion : INode
    {
        public QLQuestion(string identifier, string body, QLType type)
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

        public QLType Type
        {
            get;
        }

        public bool Validate(ref List<string> warnings, ref List<string> errors)
        {
            // TODO: check if the question has been defined already and which type it was
            return true;
        }
    }
}
