using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.SemanticAnalysis
{
    class InvalidExpressionEventArgs
    {
        public readonly string Message;

        public InvalidExpressionEventArgs(string message)
        {
            this.Message = message;
        }
    }
}
