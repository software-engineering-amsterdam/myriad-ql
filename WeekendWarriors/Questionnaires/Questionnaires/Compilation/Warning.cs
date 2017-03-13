using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Compilation
{
    public class Warning : Message
    {
        public Warning(string message) : base("Warning: " + message)
        {
           
        }

        public override bool IsError()
        {
            return false;
        }
    }
}
