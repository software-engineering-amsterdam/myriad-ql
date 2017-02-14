using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DSL.AST
{
    class QLConditional : INode
    {
        public QLConditional(INode condition, List<INode> thenStatements, List<INode> elseStatements)
        {
            this.Condition = condition;
            this.ThenStatements = thenStatements;
            this.ElseStatements = elseStatements;
        }

        public INode Condition
        {
            get;
        }

        public List<INode> ThenStatements
        {
            get;
        }

        public List<INode> ElseStatements
        {
            get;
        }

    }
}
