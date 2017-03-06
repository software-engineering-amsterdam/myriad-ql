using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis.Messages
{
    class Error : Message
    {
        public Error(string message) : base("Error: " + message)
        {

        }

        public override bool IsError()
        {
            return true;
        }
    }
}
