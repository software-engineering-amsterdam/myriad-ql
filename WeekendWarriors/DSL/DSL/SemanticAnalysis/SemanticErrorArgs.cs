using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.SemanticAnalysis
{
    public class SemanticErrorArgs : EventArgs
    {
        public readonly string Message;

        public SemanticErrorArgs(string message)
        {
            this.Message = message;
        }

    }
}
