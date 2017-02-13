using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    class QLExpression : INode
    {
        /* TODO: This class exists as a placeholder, but I'm not sure we 
         * really need it. Since we have a lot of different elements with
         * little commonality under the expression (identifiers, (unary) operators,
         * literals, etc) I think that we can substitute this class for all the child
         * classes. */
    }
}
